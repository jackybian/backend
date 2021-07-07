import cn.zsaiedu.backend.boot.util.Base64Util;
import cn.zsaiedu.backend.boot.util.HuaweiUtil;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        // 需要将endPoint/ak/sk更新为实际信息
//        String endPoint = "obs.cn-east-3.myhuaweicloud.com";
//        String ak = "FQJH6MC0ZVOPP62JELKN";
//        String sk = "v5DQAXZgqdrks7vXYIvYXRfB0Cmhmc0BZHPHI1iv";
//        String bucketName = "master-edu";                // 需要将bucketName更新为实际信息
//        // 创建ObsClient实例
//        ObsClient obsClient = new ObsClient(ak, sk, endPoint);
//        ObjectMetadata meta = new ObjectMetadata();
//        try {
//            InputStream is = new BufferedInputStream(
//                    new FileInputStream("E:/desktop.jpg"));
//            obsClient.putObject(bucketName, "desktop", is);
//        } catch (Exception ex) {
//            System.out.println("end");
//        }

        //HuaweiUtil.upload2Obs("E:/desktop.jpg", "desktop1");
        byte[] bytes = HuaweiUtil.downloadObs("desktop1");
        System.out.println(Base64Util.getBase64Code(bytes));

//        ObsObject obsObject = obsClient.getObject(bucketName, "desktop");
//        InputStream content = obsObject.getObjectContent();
//        System.out.println(obsObject.getMetadata().getContentLength());
        ByteArrayOutputStream data = new ByteArrayOutputStream();
//        try {
//                int len = 0;
//                byte[] buf = new byte[1024];
//                while ((len = content.read(buf)) != -1) {   //将byte数据读到最多buf长度的buf数组中
//
//                    data.write(buf, 0 ,len);
////                    fileOutputStream.write(buf, 0, len);         //将buf中 从0-len长度的数据写到文件中
//                }
//        } catch (Exception ex) {
//
//        }




//        List<byte[]> dataList = new ArrayList<>();
//        byte[] data = null;
//        long cnt = 0;
//        try {
//
//
//                int len = 0;
//                byte[] buf = new byte[1024];
//                while ((len = content.read(buf)) != -1) {   //将byte数据读到最多buf长度的buf数组中
//
//                    byte[] readData = new byte[len];
//                    System.arraycopy(buf, 0,readData, 0, len);
//                    dataList.add(readData);
//                    cnt = cnt + len;
////                    fileOutputStream.write(buf, 0, len);         //将buf中 从0-len长度的数据写到文件中
//                }
//
//        } catch (Exception ex) {
//        }
        BASE64Encoder encoder = new BASE64Encoder();
        System.out.println(encoder.encode(data.toByteArray()));
//        File file = new File("D:/midway_copy2.jpg");
//        FileOutputStream fileOutputStream = new FileOutputStream(file);
//        StringBuffer stringBuffer = new StringBuffer();
//        if (content != null)
//        {
//
//
//            while (true)
//            {
//                byte[] buf=new byte[1024];
//                int len=0;
//                while((len=content.read(buf))!=-1){   //将byte数据读到最多buf长度的buf数组中
//                    fileOutputStream.write(buf,0,len);         //将buf中 从0-len长度的数据写到文件中
//                }
//            }
////            reader.close();
//        }

//        PrintStream ps = new PrintStream(file);
//        ps.append(stringBuffer.toString());
//        ps.flush();
//        content.close();

//        obsClient.close();


    }

}
