package com.gxx.order.utils;

import java.io.*;

/**
 * �ļ�������
 *
 * @author Gxx
 * @module oa
 * @datetime 14-4-18 11:22
 */
public class FileUtil {
    /**
     * �����С
     */
    private static final int BUFFER_SIZE = 16 * 1024;

    /**
     * �����ļ�
     * @param src
     * @param dst
     */
    public static void copy(File src, File dst)
    {
        try
        {
            InputStream in = null;
            OutputStream out = null;
            try
            {
                in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
                out = new BufferedOutputStream(new FileOutputStream(dst), BUFFER_SIZE);
                byte[] buffer = new byte[BUFFER_SIZE];
                while (in.read(buffer) > 0)
                {
                    out.write(buffer);
                }
            } finally
            {
                if (null != in)
                {
                    in.close();
                }
                if (null != out)
                {
                    out.close();
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * ����Ŀ¼
     * @param path
     */
    public static void makeDir(String path)
    {
        File file = new File(path);

        if (!file.exists())
        {
            if (!file.mkdirs())
            {
                throw new RuntimeException("���Դ����ļ���:[" + path + "]ʧ�ܣ�");
            }
        }
    }
}
