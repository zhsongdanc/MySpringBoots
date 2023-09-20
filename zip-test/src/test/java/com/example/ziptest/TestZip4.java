package com.example.ziptest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;

public class TestZip4 {


	public static void main(String[] args) throws FileNotFoundException {

		try {
			//This is name and path of zip file to be created
			ZipFile zipFile = new ZipFile("/Users/demussong/temp/test.zip","howtodoinjava".toCharArray());

			//Add files to be archived into zip file
			ArrayList<File>filesToAdd = new ArrayList<>();
			filesToAdd.add(new File("/Users/demussong/temp/txt"));
			filesToAdd.add(new File("/Users/demussong/temp/txt/1.txt"));
			filesToAdd.add(new File("/Users/demussong/temp/txt2"));

			//Initiate Zip Parameters which define various properties
			ZipParameters parameters = new ZipParameters();
			parameters.setCompressionMethod(CompressionMethod.DEFLATE); // set compression method to deflate compression

			//DEFLATE_LEVEL_FASTEST 	- Lowest compression level but higher speed of compression
			//DEFLATE_LEVEL_FAST 		- Low compression level but higher speed of compression
			//DEFLATE_LEVEL_NORMAL 	- Optimal balance between compression level/speed
			//DEFLATE_LEVEL_MAXIMUM 	- High compression level with a compromise of speed
			//DEFLATE_LEVEL_ULTRA 		- Highest compression level but low speed
			parameters.setCompressionLevel(CompressionLevel.NORMAL);

			//Set the encryption flag to true
			parameters.setEncryptFiles(true);

			//Set the encryption method to AES Zip Encryption
			parameters.setEncryptionMethod(EncryptionMethod.AES);

			//AES_STRENGTH_128 - For both encryption and decryption
			//AES_STRENGTH_192 - For decryption only
			//AES_STRENGTH_256 - For both encryption and decryption
			//Key strength 192 cannot be used for encryption. But if a zip file already has a
			//file encrypted with key strength of 192, then Zip4j can decrypt this file
			parameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);


			//Now add files to the zip file
//		     zipFile.addFolder(new File("D:/temp/txt"), parameters);
//		     zipFile.addFolder(new File("D:/temp/txt2"), parameters);
			for(int i=1;i<=2;i++) {
				parameters.setFileNameInZip("txt/"+i+".txt");
				zipFile.addStream(new FileInputStream(new File("/Users/demussong/temp/"+"txt/"+i+".txt")), parameters);
			}

		}catch (ZipException e)
		{
			e.printStackTrace();
		}
	}
}
