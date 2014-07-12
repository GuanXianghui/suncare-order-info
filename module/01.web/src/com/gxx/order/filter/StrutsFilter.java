package com.gxx.order.filter;

import com.gxx.order.interfaces.BaseInterface;
import com.gxx.order.utils.IPAddressUtil;
import com.gxx.order.utils.TokenUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * struts���������ƿ���ueditor�ĳ�ͻ
 *
 * @author Gxx
 * @module oa
 * @datetime 14-4-18 11:21
 */
public class StrutsFilter extends StrutsPrepareAndExecuteFilter implements BaseInterface {
    /**
     * ��־������
     */
    Logger logger = Logger.getLogger(StrutsFilter.class);

    /**
     * ����
     *
     * @param req
     * @param res
     * @param chain
     * @throws java.io.IOException
     * @throws javax.servlet.ServletException
     */
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        //�����˵�url
        String url = request.getRequestURI();
        if (-1 != url.indexOf("/ueditor/jsp/")) {
            chain.doFilter(req, res);
        } else {
            //.do��β��Ҫ��Щ�ж�
            if(url.endsWith(".do")){
                String ip = IPAddressUtil.getIPAddress(request);
                String token = request.getParameter(TOKEN_KEY);
                logger.info("ip:[" + ip + "]��token:[" + token + "]");

                // 1.��tokenΪ��
                if (StringUtils.isBlank(token)) {
                    logger.error("tokenΪ��");
                    ((HttpServletResponse) res).sendRedirect(request.getContextPath() + "error.jsp");
                    return;
                }

                // 2.��token�Ƿ�ʧЧ
                if (!TokenUtil.checkToken(request, token)) {
                    logger.error("token��ʧЧ");
                    ((HttpServletResponse) res).sendRedirect(request.getContextPath() + "error.jsp");
                    return;
                }

                // 3.�е�¼(��¼Ϊajax����*.jsp����StrutsFilter)
                if (null == request.getSession().getAttribute(USER_KEY)) {
                    logger.error("�û�δ��¼");
                    ((HttpServletResponse) res).sendRedirect(request.getContextPath() + "error.jsp");
                    return;
                }

                // 4.����dao+session�û�������Ϣ
                //BaseUtil.refreshUserVisit(request);
            }

            super.doFilter(req, res, chain);
        }
    }
}
