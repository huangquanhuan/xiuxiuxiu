package xiuxiuxiu.util;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * RequestParser - 使用 commons-fileupload 对表单数据进行解析的工具类
 * 
 * @author 刘忠燏
 */
public class RequestParser {
    
    /** 图片保存的位置（相对路径） */
    private static final String UPLOAD_DIRECTORY = "uploadedImages";
    
    private static final int MEMORY_THERESHOLD = 1024 * 1024 * 3;
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40;
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50;
    
    public static ParamDto parse(HttpServletRequest request) {
        ParamDto result = new ParamDto();
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(MEMORY_THERESHOLD);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(MAX_REQUEST_SIZE);
        
        try {
            @SuppressWarnings("unchecked")
            List<FileItem> fileItems = upload.parseRequest(request);
            
            for (FileItem item : fileItems) {
                if (item.isFormField()) {
                    String name = item.getFieldName();
                    String value = item.getString("UTF-8");
                    if (result.getFileMap().containsKey(name)) {
                        value = result.getFileMap().get(name) + "," + value;
                    }
                    result.getParamMap().put(name, value);
                } else {
                    result.getFileMap().put(item.getFieldName(), item);
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
