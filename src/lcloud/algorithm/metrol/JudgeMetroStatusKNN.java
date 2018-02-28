package lcloud.algorithm.metrol;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JudgeMetroStatusKNN {
	private static JudgeMetroStatusKNN instance = new JudgeMetroStatusKNN();

	public static JudgeMetroStatusKNN getInstance() {
		if (instance == null)
			return new JudgeMetroStatusKNN();
		else
			return instance;
	}

	public int getResult(Double[] dataInput) {

		String datafile = new File("").getAbsolutePath() + File.separator + "resources" + File.separator
				+ "datafile.data"; // 预备的列车静止和运动的数据各10000条。
		try {
			List<List<Double>> datas = read(datafile);
			KNN knn = new KNN();
			return (Math
					.round(Float.parseFloat((knn.knn(datas, Stream.of(dataInput).collect(Collectors.toList()), 3)))));
		} catch (Exception e) {
			return -1;
		}
	}

	// 从数据文件中读取数据
	// @param datas 存储数据的集合对象
	// @param path 数据文件的路径
	public List<List<Double>> read(String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File(path)));
		List<List<Double>> datas = new ArrayList<List<Double>>();
		try {

			String data = br.readLine();
			List<Double> txtNumber = null; // 初始化
			while (data != null) {
				String t[] = data.split("	");// 注意，这里的空格2~4个空格。

				txtNumber = new ArrayList<Double>();
				for (int i = 0; i < t.length; i++) {

					txtNumber.add(Double.parseDouble(t[i]));
				}
				datas.add(txtNumber);
				data = br.readLine(); // 一次读入一行数据
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			br.close();
		}
		return datas;
		//
	}

	public static void main(String[] args) throws IOException {
		String path = new File("").getAbsolutePath() + File.separator + "resources" + File.separator + "testfile.data"; // 预备的列车静止和运动的数据各10000条。
		List<List<Double>> datas = JudgeMetroStatusKNN.getInstance().read(path);
		for (List<Double> data : datas) {
			//andriod call examples
			int result = JudgeMetroStatusKNN.getInstance().getResult(data.stream().toArray(Double[]::new));
		}
	}
}
