<%@ page contentType="text/html;charset=gb2312"%>
<%@ page import="java.io.File"%>
<%@ page import="java.io.*"%>
<%@ page import="org.apache.struts2.ServletActionContext" %>
<%
String root = ServletActionContext.getServletContext().getRealPath("/") + "/";//�õ���վ����·��
String fileRoute = request.getParameter("fileRoute");//�õ��ļ���
String newName = request.getParameter("newName");//�ļ�����
// ������Ӧͷ�����ر�����ļ���
response.reset();
response.setContentType("application/octet-stream; charset=GBK"); //linux
response.addHeader("Content-Disposition", "attachment; filename=\"" + newName + "\"");//linux

//�½��ļ����������
OutputStream output = null;
FileInputStream fis = null;
try{
  //�½�File����
  File f = new File(root + fileRoute);
  //�½��ļ��������������
  output = response.getOutputStream();
  fis = new FileInputStream(f);
  //����ÿ��д�뻺���С
  byte[] b = new byte[(int)f.length()];
  //out.print(f.length());
  //�������д��ͻ���
  int i = 0;
  while((i = fis.read(b)) > 0){
output.write(b, 0, i);
  }
  output.flush();
}
catch(Exception e){
  e.printStackTrace();
}
finally{
  if(fis != null){
fis.close();
fis = null;
  }
  if(output != null){
output.close();
output = null;
  }
}
%>