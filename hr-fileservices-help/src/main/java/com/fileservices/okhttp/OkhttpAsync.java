package com.fileservices.okhttp;

import com.squareup.okhttp.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2022/9/14 19:05
 */
public class OkhttpAsync {
    public static void main(String[] args) throws IOException {
        OkHttpClient httpClient = new OkHttpClient();

        Headers headers =  new Headers.Builder().add("hrgw-appname", "dos")
                .add("hrgw-timestamp","1661431878834")
                .add("hrgw-signature", "ec10b4c37097f6b73c2d02cc83a474328d6981e53208d05c5eb41593eef8e548")
                .add("staffid", "staffid")
                .add("staffname", "yiwuhe").build();

        Request request = new Request.Builder().url("http://localhost:8099/viewOnline?fileUuid=group1_M00/00/6C/CYZHs2MIcU6AaCsEAAqIgB7-Il8822.pdf&signature=5d2180afa5ec525a164104c4b40478af")
                .headers(headers).build();

        String dir = "/Users/demussong/Desktop/local";
        String fileName = "test993.pdf";

        download(httpClient, request, dir, fileName);
//        Response response = httpClient.newCall(request).execute();
//        InputStream inputStream = response.body().byteStream();
//        long len = response.body().contentLength();
//        System.out.println(len);
    }

    private static void download(OkHttpClient client, Request request, String dir, String fileName){
        // 3 异步调用
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (response.isSuccessful()){
                    // 4 文件下载
                    downlodefile(response, dir, fileName);
                }
            }

        });
    }


    private static void downlodefile(Response response, String url, String fileName) {
        InputStream inputStream = null;
        byte[] buf = new byte[2048];
        int len = 0;
        FileOutputStream outputStream = null;
        try {
            inputStream = response.body().byteStream();
            //文件大小
            long total = response.body().contentLength();
            File file = new File(url, fileName);
            outputStream = new FileOutputStream(file);
            long sum = 0;
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
                if (outputStream != null)
                    outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
