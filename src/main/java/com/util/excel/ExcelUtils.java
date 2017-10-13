package com.util.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.util.ConvertBean;





public class ExcelUtils {
	/**
	 * 下载文件存放地址
	 */
	private static final String UPLOAD_FILE = "uploadfile";
	/**
	 * 交易ex表格名字
	 */
	private static final String PAY_XLS = "paymentExcel.xls";
	/**
	 * 提现ex表格
	 */
	private static final String COOPER_XLS = "casExcel.xls";
	/**
	 * 日期样式
	 */
	private CellStyle dateCellStyle;

	/**
	 * 小数样式
	 */
	private CellStyle floatCellStyle;

	/**
	 * 日期格式，需要时可调用set返回设置
	 */
	private String dateFormat = "yyyy-MM-dd HH:mm:ss";

	/**
	 * excel 版本类型 2003
	 */
	public static final String EXCEL_TYPE_2003 = "03";
	/**
	 * excel 版本类型 2007
	 */
	public static final String EXCEL_TYPE_2007 = "07";
	
	/**
	 * excel 后缀名 2003
	 */
	public static final String EXCEL_SUFFIX_2003 = ".xls";
	/**
	 * excel 后缀名 2007
	 */
	public static final String EXCEL_SUFFIX_2007 = ".xlsx";
	
	public static final String COLUMN_CONVERT_TYPE_EXPORT = "0";
	public static final String COLUMN_CONVERT_TYPE_IMPORT = "1";

	/**
	 * excel 版本类型 默认
	 */
	public static final String EXCEL_TYPE_DEFAULT = EXCEL_TYPE_2007;

	/**
	 * 浮点型默认保留两位位小数
	 */
	private String floatFormat = "0.00";
	
	private Map<String, ? extends Object> columnMap;
	
	private List<ColumnModel> columnModelList;
	
	ConvertBean beanUtil = new ConvertBean();

	/*
	 * public List<ArrayList<String>> readExcel(String fileName, String path)
	 * throws Exception { List<ArrayList<String>> Row = new
	 * ArrayList<ArrayList<String>>(); FileInputStream fis = new
	 * FileInputStream(new File(path + "/" + fileName)); try {
	 * 
	 * XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fis); int numSheet = 0; //
	 * 解析多个sheet // for (numSheet = 0; numSheet <
	 * xssfWorkbook.getNumberOfSheets(); // numSheet++) { XSSFSheet xssfSheet =
	 * xssfWorkbook.getSheetAt(numSheet); // if (xssfSheet == null) { //
	 * continue; // } // 循环行Row for (int rowNum = 1; rowNum <=
	 * xssfSheet.getLastRowNum(); rowNum++) { XSSFRow xssfRow =
	 * xssfSheet.getRow(rowNum); if (xssfRow == null) { continue; }
	 * 
	 * // 循环列Cell ArrayList<String> cell = new ArrayList<String>(); for (int
	 * cellNum = 0; cellNum <= xssfRow.getLastCellNum(); cellNum++) { XSSFCell
	 * xssfCell = xssfRow.getCell(cellNum); if (xssfCell == null) { continue; }
	 * // if(xssfCell.getCellType() == XSSFCell.){ // // }
	 * cell.add(xssfCell.getStringCellValue()); } Row.add(cell); } // } //
	 * logger.info("AllCityExcel getdata success"); } catch (IOException e) { //
	 * logger.error("AllCityExcel getdata error",e); }
	 * 
	 * return Row; }
	 */

	/**
	 * 解析Excel到List<Map<String, Object>>
	 * 
	 * @param path
	 *            Excel文件路径
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> readExcel(String path) throws Exception {
		Workbook work = getWorkbook(path);
		List<Map> dataMap = readExcel(work);
		return dataMap;
	}

	/**
	 * 解析Excel文件到Map列表<br/>
	 * 
	 * @param excelFile
	 *            Excel文件
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> readExcel(File excelFile) throws Exception {
		Workbook work = getWorkbook(excelFile);
		List<Map> dataMap = readExcel(work);
		return dataMap;
	}

	/**
	 * 将Excel自动解析到JavaBean列表
	 * 
	 * @param excelPath
	 *            Excel文件路径
	 * @param columnMap
	 *            列配置Map( 解析列格式 key:实体bean中属性名 value:Excel中列名)
	 * @param clazz
	 *            JavaBean 类
	 * @return
	 * @throws Exception
	 */
	public <T extends Object> List<T> readExcel(String excelPath,
			Map<String, String> columnMap, Class<T> clazz) throws Exception {
		Workbook work = getWorkbook(excelPath);
		List<T> list = readExcel(work,clazz);
		return list;
	}

	/**
	 * 将Excel自动解析到JavaBean列表（List）
	 * 
	 * @param excelFile
	 * @param columnMap
	 *            解析列格式 key:实体bean中属性名 value:Excel中列名
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public <T extends Object> List<T> readExcel(File excelFile,
			Map<String, String> columnMap, Class<T> clazz) throws Exception {
		Workbook work = getWorkbook(excelFile);
		List<T> list = readExcel(work,clazz);
		return list;
	}

	/**
	 * 处理解析Excel时列配置Map 由 key:实体bean中属性名 value:Excel中列名 反过来，既变为 key:Excel中列名
	 * value:实体bean中属性名已方便以下的解析。如： key=ID,value=编号 --> key=编号,value=ID
	 * 
	 * @param columnMap
	 *            key:实体bean中属性名 value:Excel中列名
	 * @return java.util.Map<String, String> key:Excel中列名 value:实体bean中属性名
	 */
	@SuppressWarnings("unused")
	private Map<String, String> doReadExcelColumnMap(
			Map<String, String> columnMap) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		String value = null;
		if (columnMap != null && !columnMap.isEmpty()) {
			Set<String> keySet = columnMap.keySet();
			for (String key : keySet) {
				value = columnMap.get(key);
				if (value != null && !value.isEmpty()) {
					// 键值对换
					map.put(value, key);
				}
			}
		}
		return map;
	}
	
	public Map<String,? extends Object> columnFormatToExport(List<ColumnModel> columnModels){
		return columnFormat(columnModels,COLUMN_CONVERT_TYPE_EXPORT);
	}
	
	public Map<String,? extends Object> columnFormatToImport(List<ColumnModel> columnModels){
		return columnFormat(columnModels,COLUMN_CONVERT_TYPE_IMPORT);
	}
	public Map<String,? extends Object> columnFormatToExport(){
		return columnFormat(columnModelList,COLUMN_CONVERT_TYPE_EXPORT);
	}
	
	public Map<String,? extends Object> columnFormatToImport(){
		return columnFormat(columnModelList,COLUMN_CONVERT_TYPE_IMPORT);
	}
	
	public Map<String,? extends Object> columnFormat(List<ColumnModel> columnModels,String convertType){
		//每个对象只转换一次
		if(this.columnMap != null){
			return this.columnMap;
		}
		Map<String, Object> columnMap = new LinkedHashMap<String, Object>();
		if(columnModels == null || columnModels.isEmpty()){
			return columnMap;
		}
		// 把 if 放外层可以减少判断次数
		if(COLUMN_CONVERT_TYPE_EXPORT.equals(convertType)){//如果是导出 ，map中的key存属性名称
			for (ColumnModel columnModel : columnModels) {
				columnMap.put(columnModel.getColumnName(), columnModel);
			}
		}else if(COLUMN_CONVERT_TYPE_IMPORT.equals(convertType)){//如果是导入 ，map中的key存列名
			for (ColumnModel columnModel : columnModels) {
				//如果是导出 ，map中的key存属性名称
				columnMap.put(columnModel.getColumnTitle(), columnModel);
			}
		}
		this.setColumnMap(columnMap);
		return columnMap;
	}

	/**
	 * 解析Excel到实体bean
	 * 
	 * @param work
	 * @param columnMap
	 *            解析列格式 key:实体bean中属性名 value:Excel中列名
	 * @param clazz
	 *            实体类
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public final <T extends Object> List<T> readExcel(Workbook work, Class<T> clazz) throws Exception {

		// 将键值对换以及为空时创建对象
		//转换列配置到map
		this.columnFormatToImport();
		
		//转换列配置到map
//		this.columnFormatToExport(this.columnModelList);

		List<T> list = new ArrayList<T>();
		// 存放数据
		// 得到第一个选项卡对象
		Sheet sheet = work.getSheetAt(0);
		// 得到第一行（列头）
		Row columnRow = sheet.getRow(0);
		List<String> columnList = new ArrayList<String>();

		// 将所有列名信息存放到List
		for (int cellNum = 0; cellNum <= columnRow.getLastCellNum(); cellNum++) {
			Cell cell = columnRow.getCell(cellNum);
			if (cell == null) {
				columnList.add("");
				continue;
			}
			// 得到列名
			String value = cell.getStringCellValue().trim();
			columnList.add(value);
		}

		String column = null;
		Row row = null;
		ColumnModel columnModel = null;
		HashMap<String, Object> dataMap = null;
		Cell cell = null;
		// 遍历excel所有行
		for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
			row = sheet.getRow(rowNum);
			// 如果为空行，不做处理
			if (row == null) {
				continue;
			}

			dataMap = new HashMap<String, Object>();
			// 遍历该行所有单元格（这里遍历大小为第一行列头中列的个数）
			for (int cellNum = 0; cellNum <= columnList.size(); cellNum++) {
				cell = row.getCell(cellNum);
				if (cell == null) {
					continue;
				}
				// 得到单元格内容
				Object value = getCellValue(cell);
				column = columnList.get(cellNum);
				if (columnMap.get(column) != null) {
					//如果是列模型对象，从中取出属性名称
					Class cls = columnMap.get(column).getClass();
					if(cls.isAssignableFrom(ColumnModel.class)){
						columnModel = (ColumnModel)columnMap.get(column);
						column = columnModel.getColumnName();
						value = columnModel.parsingFormat(value);
					}else if(cls.isAssignableFrom(String.class)){
						column = (String)columnMap.get(column);
					}
					dataMap.put(column, value);
				}
			}
			// 如果是需要返回Map，则不需要再进一步转换
			if (clazz.isAssignableFrom(Map.class)) {
				list.add((T) dataMap);
			} else {
				// 转为对应实体
				T obj = beanUtil.convertMap(clazz, dataMap);
				list.add(obj);
			}
		}
		return list;
	}

	/**
	 * 解析Excel到Map 第一行的列头不会存储， 返回的List中的Map的格式为： key=列标题(列头) ,value=当前行当前列的值
	 * 
	 * @param work
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> readExcel(Workbook work) throws Exception {

		return this.readExcel(work, Map.class);
	}

	/**
	 * 根据单元格类型获取内容(Object)
	 * 
	 * @param cell
	 * @return
	 */
	private Object getCellValue(Cell cell) {
		if (cell == null) {
			return null;
		}
		// 时间对象 特殊处理
		int dataFormat = cell.getCellStyle().getDataFormat();

		// 日期格式
		if (dataFormat == 14 || dataFormat == 178 || dataFormat == 180
				|| dataFormat == 181 || dataFormat == 182) {

			return cell.getDateCellValue();
		}

		Object value = null;
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC:
			// value = new DecimalFormat("0.##########").format(cell
			// .getNumericCellValue());
			value = cell.getNumericCellValue();
			break;
		case Cell.CELL_TYPE_STRING:
			// value = cell.getStringCellValue();
			// value = cell.getRichStringCellValue().toString();
			value = cell.getRichStringCellValue().toString().trim();
			break;
		case Cell.CELL_TYPE_FORMULA:
			// value = String.valueOf(cell.getCellFormula());
			value = cell.getCellFormula();
			break;
		case Cell.CELL_TYPE_BLANK:
			// value = String.valueOf(cell.getStringCellValue());
			// value = String.valueOf(cell.getRichStringCellValue().toString());
			value = cell.getRichStringCellValue();
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			// value = String.valueOf(cell.getBooleanCellValue());
			value = cell.getBooleanCellValue();
			break;
		case Cell.CELL_TYPE_ERROR:
			// value = String.valueOf(cell.getErrorCellValue());
			value = cell.getErrorCellValue();
			break;
		}
		return value;
	}

	/**
	 * 解析文件到Workbook对象
	 * 
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public Workbook getWorkbook(String filePath) throws Exception {

		return getWorkbook(new File(filePath));
	}

	/**
	 * 解析文件到Workbook对象
	 * 
	 * @param file
	 *            Excel文件对象
	 * @return
	 * @throws Exception
	 */
	public Workbook getWorkbook(File file) throws Exception {
		FileInputStream fis = new FileInputStream(file);
		Workbook work = WorkbookFactory.create(fis);
		fis.close();
		return work;
	}

	/**
	 * 根据文件路径与文件名称 拼接为绝对路径
	 * @param filePath 文件存储路径 （如果目录不存在，会创建目录）
	 * @param fileName文件存储名称 文件名不需要传后缀，如果带了后缀，
	 * 系统不会处理，反而可能会导致格式与后缀不符
	 * @param excelType 版本类型 03：excel2003 07：excel2007
	 * @param dataList 数据列表
	 * @return 返回创建好的文件绝对路径
	 * @throws Exception
	 */
	public String createExcel(String filePath, String fileName, String excelType,
			List<? extends Object> dataList) throws Exception{
		String path = getFilePath(filePath,fileName,excelType);
		return createExcel(path,excelType,dataList);
	}
	
	/**
	 * 根据文件路径与文件名称 拼接为绝对路径
	 * @param filePath 文件存储路径 （如果目录不存在，会创建目录）
	 * @param fileName 文件存储名称 文件名不需要传后缀，如果带了后缀，
	 * 系统不会处理，反而可能会导致格式与后缀不符
	 * @param excelType 版本类型 03：excel2003 07：excel2007
	 * @return 返回构建好的绝对路径
	 */
	private String getFilePath(String filePath, String fileName, String excelType){
		String path = filePath;
		if( !path.endsWith(File.separatorChar + "")){
			path += File.separatorChar;
		}
		path += fileName;
		//如果传入的文件名中有后缀，不再添加文件名
		if(path.endsWith(EXCEL_SUFFIX_2003) || path.endsWith(EXCEL_SUFFIX_2007)){
			return path;
		}
		if(EXCEL_TYPE_2003.equals(excelType)){
			path += EXCEL_SUFFIX_2003;
		}else if(EXCEL_TYPE_2007.equals(excelType)){
			path += EXCEL_SUFFIX_2007;
		}else{
			path = getFilePath(filePath,fileName,EXCEL_TYPE_DEFAULT);
		}
		return path;
	}
	
	/**
	 * 创建excel并写入指定文件地址
	 * @param absPath 要存储的绝对路径 包含路径+文件名
	 * @param excelType excel 版本类型 03：excel2003 07：excel2007
	 * @param columnMap 列配置
	 * @param dataList 数据列表
	 * @return
	 * @throws Exception
	 */
	public String createExcel(String absPath, String excelType,
			
			List<? extends Object> dataList) throws Exception{
		Workbook work = createExcel(excelType,dataList);
		writeWorkbook(work, absPath);
		return absPath;
	}
	
	/**
	 * 创建excel并写入指定文件地址
	 * @param absPath 要存储的绝对路径 包含路径+文件名
	 * @param excelType excel 版本类型 03：excel2003 07：excel2007
	 * @param columnMap 列配置
	 * @param dataList 数据列表
	 * @return
	 * @throws Exception
	 */
	public void createExcel(OutputStream out, String excelType,List<? extends Object> dataList) throws Exception{
		Workbook work = createExcel(excelType,dataList);
		writeWorkbook(work, out);
	}

	/**
	 * 根据传入的excel版本标记，创建对应版本的excel文件 
	 * 
	 * @param excelType excel 版本类型 03：excel2003 07：excel2007
	 * @param columnMap 列配置
	 * @param dataList 数据列表
	 * @return
	 * @throws Exception
	 */
	public Workbook createExcel(String excelType,
			
			List<? extends Object> dataList) throws Exception {
		Workbook work = null;
		
		// 如果是要导出2003
		if (EXCEL_TYPE_2003.equals(excelType)) {
			work = new HSSFWorkbook();// 创建excel
		} else if (EXCEL_TYPE_2007.equals(excelType)) {
			work = new XSSFWorkbook();
		} else {
			work = new HSSFWorkbook();// 创建excel
		}
		//如果是多个格式的excel
		Object data0 = dataList.get(0);
		if(data0.getClass().isAssignableFrom(ExcelDataBox.class)){
			ExcelDataBox dataBox = null;
			for (Object t : dataList) {
				dataBox = (ExcelDataBox)t;
				//清空上一次的列配置信息
				this.setColumnMap(null);
				this.setColumnModelList(dataBox.getColumnModelList());
				createExcelByWorkBook(work,dataBox.getDataList());
			}
		}else{
			createExcelByWorkBook(work,dataList);
		}
		return work;
	}
	
	/**
	 * 创建excel 2003 并写入磁盘，操作成功返回写入的文件绝对路径
	 * @param filePath 文件路径
	 * @param fileName 文件名称
	 * @param columnMap 列配置map
	 * @param dataList 数据列表
	 * @return 创建文件返回文件绝对路径
	 * @throws Exception
	 */
	public String createExcel2003(String filePath, String fileName,
			
			List<? extends Object> dataList) throws Exception {
		String absPath = getFilePath(filePath,fileName,EXCEL_TYPE_2003);
		return createExcel(absPath,EXCEL_TYPE_2003,dataList);
	}
	
	/**
	 * 
	 * @param absPath
	 * @param columnMap
	 * @param dataList
	 * @return
	 * @throws Exception
	 */
	public String createExcel2003(String absPath,
			
			List<? extends Object> dataList) throws Exception {
		return createExcel(absPath,EXCEL_TYPE_2003,dataList);
	}
	
	public String createExcel2007(String filePath, String fileName,
			
			List<? extends Object> dataList) throws Exception {
		String absPath = getFilePath(filePath,fileName,EXCEL_TYPE_2007);
		return createExcel(absPath,EXCEL_TYPE_2007,dataList);
	}
	
	public String createExcel2007(String absPath,
			
			List<? extends Object> dataList) throws Exception {
		return createExcel(absPath,EXCEL_TYPE_2007,dataList);
	}
	



	/**
	 * 根据创建好的Workbook对象添加excel内容 （当前已兼容excel2003与2007版本）
	 * @param work excel 对象
	 * @param columnMap 列配置
	 * @param dataList 数据列表
	 * @return 返回创建好内容的Workbook
	 * @throws Exception
	 */
	private Workbook createExcelByWorkBook(Workbook work,
			List<? extends Object> dataList) throws Exception {
		// Sheet sheet = work.createSheet("Sheet1");
		Sheet hssf_w_s = null;
		if(work.getNumberOfSheets() <= 0){
			hssf_w_s = work.createSheet("Sheet1");
		}else{
			hssf_w_s = work.getSheetAt(0);
		}
		Row hssf_w_r = null;// 创建一行
		Cell hssf_w_c = null;// 每个单元格

		CreationHelper factory = work.getCreationHelper();

		Set<String> columnSet = null;

		short columnIdx = 0;
		
		int rowIdx = hssf_w_s.getLastRowNum() + 1;// 要创建行的行号
		int lastRow = hssf_w_s.getLastRowNum();
		if(lastRow == 0){
			Row tr = hssf_w_s.getRow(lastRow);
			if(tr == null ||tr.getLastCellNum() < 0){
				rowIdx = 0;
			}
		}

		Object cellValue = null;
		ColumnModel columnModel = null;
		//转换列配置到map
		this.columnFormatToExport(this.columnModelList);

		if (columnMap != null && !columnMap.isEmpty()) {
			columnSet = columnMap.keySet();// 获得列属性set集合

			hssf_w_r = hssf_w_s.createRow(rowIdx++);// 创建列标题行

			// 创建列样式
			CellStyle cellStyle = work.createCellStyle();
			Font font = work.createFont();
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			font.setFontHeightInPoints((short) 10);
			font.setFontName("宋体");// 设置标题字体
			cellStyle.setFont(font);

			cellStyle = work.createCellStyle();
			cellStyle.setFont(font);// 设置列标题样式
			
			//内容居中
			cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
			
			// 生成第一行标题列
			for (String property : columnSet) {
				hssf_w_c = hssf_w_r.createCell(columnIdx);
				hssf_w_c.setCellType(Cell.CELL_TYPE_STRING);
				Object obj = columnMap.get(property);
				if(obj.getClass().isAssignableFrom(ColumnModel.class)){
					columnModel = (ColumnModel)obj;
					cellValue = columnModel.getColumnTitle();
				}else if(obj.getClass().isAssignableFrom(String.class)){
					cellValue = obj;
				}
				RichTextString hssfString = factory
						.createRichTextString((String)cellValue);
				hssf_w_c.setCellValue(hssfString);
				hssf_w_c.setCellStyle(cellStyle);
				columnIdx++;
			}
		}

		// 如果没有数据，则返回
		if (dataList == null || dataList.isEmpty()) {

			return work;
		}

		// 除列标题以外的数据行数
		int row_count = dataList.size();

		Map<String, Object> objMap = null;
		Object objValue = null;
		Object pojo = null;
		RichTextString hssfString = null;

		for (int i = 0; i < row_count; i++) {
			columnIdx = 0;

			hssf_w_r = hssf_w_s.createRow(rowIdx);

			pojo = dataList.get(i);
			// 如果本来就是Map则不需要利用反射进行转换
			if (pojo instanceof Map) {
				objMap = (Map) pojo;
			} else {
				// 将javabean转换为map集合
				objMap = beanUtil.convertBean(pojo);
			}

			// 如果没有列标题行，则按对象所有属性生成单元格(不支持List中有不同类(不是同一个class)的对象)
			if (columnSet == null || columnSet.isEmpty()) {
				columnSet = objMap.keySet();
			}

			for (String property : columnSet) {
				hssf_w_c = hssf_w_r.createCell(columnIdx);
				

				// 取出对应属性的Object值
				if(property != null && property.indexOf(".") > 0){
					//处理 a.b.c格式的对象嵌套
					String[] p_sz = property.split("\\.");
					Map<String, Object> o = objMap;
					for (int j = 0; j < p_sz.length - 1; j++) {
						if( o.get(p_sz[j]) != null){
							o = beanUtil.convertBean(o.get(p_sz[j]));
						}
					}
					objValue = o.get(p_sz[p_sz.length-1]);
					
//					objValue = objMap.get(property);
				}else{
					objValue = objMap.get(property);
				}
				
				//如果有格式化定义，则进行格式化
				Object obj =  null;
				if(columnMap != null && (obj=columnMap.get(property)).getClass().isAssignableFrom(ColumnModel.class)){
					columnModel = (ColumnModel)obj;
					objValue = columnModel.dataFormat(i,dataList,objValue);
				}

				// 判断object值类型
				if (objValue == null) {
					hssf_w_c.setCellType(Cell.CELL_TYPE_BLANK);
				} else if (objValue instanceof String) {// 字符型
					hssf_w_c.setCellType(Cell.CELL_TYPE_STRING);
					hssfString = factory
							.createRichTextString((String) objValue);
					hssf_w_c.setCellValue(hssfString);
				} else if (objValue instanceof Date) {// 日期类型
					hssf_w_c.setCellType(Cell.CELL_TYPE_NUMERIC);
					hssf_w_c.setCellValue((Date) objValue);

					hssf_w_c.setCellStyle(getDateCellStyle(work));
				} else if (objValue instanceof Integer) {// 整形
					hssf_w_c.setCellType(Cell.CELL_TYPE_NUMERIC);
					hssf_w_c.setCellValue((Integer) objValue);
				} else if (objValue instanceof Float) {
					hssf_w_c.setCellValue((Float) objValue);

					hssf_w_c.setCellStyle(getFloatCellStyle(work));

				} else {// 其他情况做字符串处理
					hssf_w_c.setCellType(Cell.CELL_TYPE_STRING);
					hssfString = factory.createRichTextString(String
							.valueOf(objValue));
					hssf_w_c.setCellValue(hssfString);
				}

				// 自动列宽效果不是很理想，经过测试每个单元格创建完成都设置一次效果比较好，如果在大数据量的情况下对性能有影响，可以去除
				// 果然很影响性能
				// hssf_w_s.autoSizeColumn(columnIdx);

				columnIdx++;
			}

			rowIdx++;

		}

		// 经过测试，在数据生成完毕后再设置自动列宽效果要好些
		int rowSiz = hssf_w_s.getRow(0).getLastCellNum();
		for (int i = 0; i < rowSiz; i++) {
			hssf_w_s.autoSizeColumn(i);
		}
		return work;
	}
	
	public void writeWorkbook(Workbook work,String absPath) throws Exception{
		File excelFile = new File(absPath);
		if(!excelFile.getParentFile().exists()){
			excelFile.getParentFile().mkdirs();
		}
		writeWorkbook(work,new FileOutputStream(excelFile));
	}
	
	public void writeWorkbook(Workbook work,OutputStream out) throws Exception{
		work.write(out);
		out.flush();
		out.close();
	}

	/**
	 * 获取(或创建)Excel日期格式化样式(当前同一个Excel文件只能对一种类型数据同意设置为一种格式)
	 * 
	 * @param workbook
	 * @return
	 */
	private CellStyle getDateCellStyle(Workbook workbook) {
		if (dateCellStyle == null) {
			dateCellStyle = workbook.createCellStyle();
			DataFormat format = workbook.createDataFormat();
			dateCellStyle.setDataFormat(format.getFormat(getDateFormat()));
		}

		return dateCellStyle;
	}

	/**
	 * 获取浮点型格式(当前同一个Excel文件只能对一种类型数据同意设置为一种格式)
	 * 
	 * @param workbook
	 * @return
	 */
	private CellStyle getFloatCellStyle(Workbook workbook) {
		if (floatCellStyle == null) {
			floatCellStyle = workbook.createCellStyle();
			// 当前保留一位小数，可以转成String再判断小数位数再动态设置，不过那样会影响性能
			floatCellStyle.setDataFormat(workbook.createDataFormat().getFormat(
					getFloatFormat()));
		}

		return floatCellStyle;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public String getFloatFormat() {
		return floatFormat;
	}

	public void setFloatFormat(String floatFormat) {
		this.floatFormat = floatFormat;
	}
	
	
	/**
	 * 导出eX表格
	 * @param request
	 * @param path 获取服务器项目地址
	 * @param miaoshu 标题列
	 * @param areaList 全部内容
	 * @param countList 合计总数列
	 * @return success成功,error失败或抛出异常
	 * @author 13376
	 */
	public static String downloadex(HttpServletRequest request,String path,String flag,
			List<Map<String,Object>> miaoshu,
			List<Map<String,Object>> areaList,
			List<Map<String,Object>> countList
			){
		String dir = "";
		if (flag=="payment") {
			dir = path+"\\"+UPLOAD_FILE+""+"\\"+PAY_XLS;//下载到指定文件夹
		}else{
			dir = path+"\\"+UPLOAD_FILE+""+"\\"+COOPER_XLS;//下载到指定文件夹
		}
		System.out.println("地址："+dir);
		 List<ExcelDataBox> dataList = new ArrayList<ExcelDataBox>();
			ExcelDataBox box = new ExcelDataBox();
			box.setDataList(miaoshu);
			dataList.add(box);
			
			box = new ExcelDataBox();
			box.setDataList(areaList);
			dataList.add(box);
			
			box = new ExcelDataBox();
			box.setDataList(countList);
			dataList.add(box);
			try {
				ExcelUtils util = new ExcelUtils();
				Workbook workBook = util.createExcel("03", dataList);//传入03格式
				OutputStream out = new FileOutputStream(new File(dir));
				workBook.write(out);
				out.flush();
				out.close();
				System.out.println("导入成功"+dir);
			} catch (Exception e) {
				e.printStackTrace();
				return "error";
			}
			return "success";
		
	}

	public static void main(String[] args) {
		Map<String, String> columnMap = new LinkedHashMap<String, String>();
	/*	columnMap.put("id", "编号");
		columnMap.put("date", "修改日期");
		columnMap.put("desc", "描述");*/
		
		/*MINSHENG_BANK_COLUMNS.add(new ColumnModel("cardNo", "收款账号"));
		MINSHENG_BANK_COLUMNS.add(new ColumnModel("custName", "收款账户户名"));
		MINSHENG_BANK_COLUMNS.add(new ColumnModel("txamt", "交易金额"));
		MINSHENG_BANK_COLUMNS.add(new ColumnModel("custId", "付款备注"));*/
		
		List<Map> columnModelList = new ArrayList<Map>();
		
		List<Map> areaList = new ArrayList<Map>();
		
		List<Map> miaoshu = new ArrayList<Map>();
		
		
		Map<String, Object> app = new LinkedHashMap<String, Object>();
		for (int i = 0; i <6; i++) {
			app.put(""+i+"", ""+i+"");			
		}
		miaoshu.add(app);

	/*	app = new LinkedHashMap<String, Object>();
		app.put("1", "交易总金额");
		app.put("2","20,000.00");
		miaoshu.add(app);*/
		
		app = new HashMap<String, Object>();
		app.put("cardNo", "6221885610004689909");
		app.put("custName","谢锦中");
		app.put("txamt", "10,000.00");
		app.put("custId", "18116260879");
		areaList.add(app);
		
		app = new HashMap<String, Object>();
		app.put("cardNo", "6221885610004689909");
		app.put("custName","谢锦中");
		app.put("txamt", "10,000.00");
		app.put("custId", "18116260879");
		areaList.add(app);
		
		Date date = new Date();
		String dir = "d:\\excelTests.xls";
		
		List<ExcelDataBox> dataList = new ArrayList<ExcelDataBox>();
		ExcelDataBox box = new ExcelDataBox();
		box.setDataList(miaoshu);
		dataList.add(box);
		
		box = new ExcelDataBox();
		//box.setColumnModelList(ExcelColumnMap.MINSHENG_BANK_COLUMNS);
		box.setDataList(areaList);
		dataList.add(box);
		try {
			ExcelUtils util = new ExcelUtils();
			Workbook workBook = util.createExcel("03", dataList);//传入03格式

			OutputStream out = new FileOutputStream(new File(dir));
			workBook.write(out);
			out.flush();
			out.close();
			System.out.println("文件导出完成：" + dir);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Map<String, ? extends Object> getColumnMap() {
		return columnMap;
	}

	public void setColumnMap(Map<String, ? extends Object> columnMap) {
		this.columnMap = columnMap;
	}

	public List<ColumnModel> getColumnModelList() {
		return columnModelList;
	}

	public void setColumnModelList(List<ColumnModel> columnModelList) {
		this.columnModelList = columnModelList;
	}
	
}
