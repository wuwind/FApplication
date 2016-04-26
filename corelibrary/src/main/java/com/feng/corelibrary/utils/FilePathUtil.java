package com.feng.corelibrary.utils;

import android.os.Environment;
import android.os.StatFs;

import java.io.File;

/**
 * SD卡目录工具类
 * 
 * @author WuRS
 * 
 */
public class FilePathUtil {

	private static final String MAIN_PATH = "wusysFile";
	private static final String ERROR_PATH = "error";
	private static final String CACHE_PATH = "cache";

	/**
	 * 文件主目录
	 * 
	 * @return
	 */
	public static String getMainPath() throws Exception {
		try {
			String path = getSDPath() + "/" + MAIN_PATH + "/";
			File dir = new File(path);
			if (!dir.exists())
				dir.mkdirs();
			return path;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 获取错误日志文件夹
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getErrorPath() throws Exception {
		try {
			String path = getMainPath() + ERROR_PATH;
			File dir = new File(path);
			if (!dir.exists())
				dir.mkdirs();
			return path;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 获取图片缓存文件夹
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getCachePath() throws Exception {
		try {
			String path = getMainPath() + CACHE_PATH;
			File dir = new File(path);
			if (!dir.exists())
				dir.mkdirs();
			return path;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @param dirName
	 *            当前目录名称
	 * @return 获取主目录下的子目录，不存在目录将创建目录
	 * @throws Exception
	 */
	public static File getDir(String dirName) throws Exception {
		File dir = new File(getMainPath(), dirName);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return dir;
	}

	/**
	 * 删除文件或文件夹
	 * 
	 * @param filePath
	 */
	public static void deleteFile(String filePath) {
		deleteFile(new File(filePath));
	}

	/**
	 * 删除文件或文件夹
	 */
	public static void deleteFile(File file) {
		if (file != null && file.exists()) {
			if (file.isDirectory()) {
				File[] files = file.listFiles();
				if (files == null) {
					return;
				}
				for (int i = 0; i < files.length; i++) {
					deleteFile(files[i]);
				}
			} else {
				file.delete();
			}
		}
	}

	// 取SD卡路径
	public static String getSDPath() throws Exception {
		File sdDir = null;
		boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
		if (sdCardExist) {
			sdDir = Environment.getExternalStorageDirectory(); // 获取根目录
		}
		if (sdDir != null) {
			return sdDir.toString();
		} else {
			throw new Exception("SDcard不存在");
		}
	}

	// 判断所传入的文件是否大于SDcard剩余的容量
	public static boolean isSDSizeEnough(long fileSize) throws Exception {
		try {
			if ("".equals(getSDPath())) {
				return false;
			}
		} catch (Exception e) {
			throw e;
		}
		File path = Environment.getExternalStorageDirectory();
		StatFs statfs = new StatFs(path.getPath());
		long blockSize = statfs.getBlockSize();
		long availableBlocks = statfs.getAvailableBlocks();
		if (availableBlocks * blockSize > fileSize)
			return true;
		else
			return false;
	}

}
