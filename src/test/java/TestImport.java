import com.tuyendev.mmeo.transfers.ExcelToObject;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class TestImport {

    private  StudentDTO studentDTO;

    @Test
    public void testImport1(){
        File file = new File("test.xls");
        try {
            List<StudentDTO> datas = ExcelToObject.input(new FileInputStream(file))
                    .forClass(StudentDTO.class)
                    .titleIndex(1)
                    .dataIndex(2).transfer();
            for (StudentDTO data : datas) {
                System.out.println(data.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public StudentDTO getStudentDTO() {
        return studentDTO;
    }

    public void setStudentDTO(StudentDTO studentDTO) {
        this.studentDTO = studentDTO;
    }
}
