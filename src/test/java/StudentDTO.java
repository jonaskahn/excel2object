import com.tuyendev.mmeo.annos.ColumnInfo;
import com.tuyendev.mmeo.annos.SheetInfo;

import java.math.BigDecimal;
import java.util.Date;

@SheetInfo(headerName = "Student", name = "student", headerSkipped = false)
public class StudentDTO {

    @ColumnInfo(name = "No.", index = 0)
    private Integer id;

    @ColumnInfo(name = "Nam", index = 1)
    private String name;

    @ColumnInfo(name = "Age", index = 2)
    private Integer age;

    @ColumnInfo(name = "Birth day", index = 3, format = "dd/MM/yyyy")
    private Date birthDay;

    @ColumnInfo(name = "Average", index = 4)
    private Float avg;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public Float getAvg() {
        return avg;
    }

    public void setAvg(Float avg) {
        this.avg = avg;
    }


    @Override
    public String toString() {
        return "StudentDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", birthDay=" + birthDay +
                ", avg=" + avg +
                '}';
    }
}
