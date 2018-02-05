import com.tuyendev.mmeo.transfers.ObjectToExcel;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestExport {

    List<StudentDTO> studentDTOS;

    @Before
    public void setUp() throws Exception {
        StudentDTO s1 = new StudentDTO();
        s1.setId(1);
        s1.setAge(20);
        s1.setName("Dan");
        s1.setBirthDay(new Date());

        StudentDTO s2 = new StudentDTO();
        s2.setId(1);
        s2.setAge(21);
        s2.setName("Michel");
        s2.setAvg(7.5f);

        studentDTOS = new ArrayList<>();
        studentDTOS.add(s1);
        studentDTOS.add(s2);
    }


    @Test
    public void testExport1(){
        try {
            ByteArrayOutputStream obj  = ObjectToExcel.input(studentDTOS).forClass(StudentDTO.class).transfer();
            File f = new File("test.xls");
            if(f.createNewFile()){
                OutputStream outputStream = new FileOutputStream(f);
                obj.writeTo(outputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<StudentDTO> getStudentDTOS() {
        return studentDTOS;
    }

    public void setStudentDTOS(List<StudentDTO> studentDTOS) {
        this.studentDTOS = studentDTOS;
    }
}
