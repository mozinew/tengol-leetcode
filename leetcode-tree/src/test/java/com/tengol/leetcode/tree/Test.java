package com.tengol.leetcode.tree;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Objects;

/**
 * Test
 *
 * @author dongrui
 * @date 2020/6/3 12:42
 */
public class Test {
    public static void main(String[] args) {
        String source = "E:\\MyIt\\Interview\\21天互联网Java进阶面试训练营（分布式篇）完整版";
        String fileType = ".mp4";
        String target = "E:\\MyIt\\40";
        int count = move(fileType, source, target, "【www.zxit8.com】");
        System.out.println("移动成功，共移动了" + count + "个文件");

//        boolean result = moveFile(new File("E:\\MyIt\\Interview\\21天互联网Java进阶面试训练营（分布式篇）完整版\\004、作业：系统分析一下，自己距离大厂offer差在哪里？\\004、作业：系统分析一下，自己距离大厂offer差在哪里？ 【www.zxit8.com】.mp4"),new File(target.concat("\\1.mp4")));
//        System.out.println(result);
    }

    private static int move(String fileType, String source, String target, String replaceStr) {
        int fileNum = 0;

        // 判断源目录是否存在
        File sourceDir = checkAndGetDir(source);
        // 判断目标目录是否存在
        File targetDir = checkAndGetDir(target);
        // 获取源目录下的所有目录
        File[] files = sourceDir.listFiles();
        if (Objects.isNull(files) || files.length == 0) {
            return 0;
        }
        // 遍历
        for (File file : files) {
            // 处理目录下文件
            if (file.isDirectory()) {
                File[] fs = file.listFiles();
                if (Objects.isNull(fs) || fs.length == 0) {
                    continue;
                }
                for (File s : fs) {
                    if (s.getName().contains(fileType)) {
                        String newFileName = s.getName().replace(replaceStr, "");
                        File t = new File(targetDir.getAbsolutePath().concat("\\").concat(newFileName));
                        boolean result = moveFile(s, t);
                        if(result) {
                            fileNum++;
                        }
                    }
                }
            } else if (file.getName().contains(fileType)) {
                File t = new File(targetDir.getName().concat("\\").concat(file.getName().replace(replaceStr,"")));
                boolean result = moveFile(file, t);
                if(result) {
                    fileNum++;
                }
            }
        }

        return fileNum;
    }

    private static File checkAndGetDir(String dirUrl) {
        // 判断源目录是否存在
        File dir = new File(dirUrl);
        if (!dir.isDirectory() || !dir.exists()) {
            throw new IllegalArgumentException("value of source is invalid");
        }
        return dir;
    }

    private static boolean moveFile(File source, File target) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis  = new FileInputStream(source);//创建输入流对象
            fos = new FileOutputStream(target); //创建输出流对象
            byte dataArray[] = new byte[1024*8];
            int len = 0;
            while((len = fis.read(dataArray))!=-1){
                fos.write(dataArray,0,len);
            }
            System.out.println("移动文件：" + target.getName());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (Objects.nonNull(fis)) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
            if (Objects.nonNull(fos)) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return true;
    }

//    private static boolean moveFile(File source, File target) {
//        FileChannel inputChannel = null;
//        FileChannel outputChannel = null;
//        try {
//            inputChannel = new FileInputStream(source).getChannel();
//            outputChannel = new FileOutputStream(target).getChannel();
//            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
//            System.out.println("移动文件：" + target.getName());
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        } finally {
//            if (Objects.nonNull(inputChannel)) {
//                try {
//                    inputChannel.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    return false;
//                }
//            }
//            if (Objects.nonNull(outputChannel)) {
//                try {
//                    outputChannel.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
}
