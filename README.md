
# Excel bidirectional transfer to Java Object

## What is it?
This is a small project that will help you interact with excel file and object are easier than. You can export a collection of Objects to Excel file with title, header, basic style and you also can read from Excel file to Object Java with less than code, it bases on Apache Poi.
## How to use?
You need declare two annotations ***@ColumnInfo***,  ***@SheetInfo*** for each class that will be used to transfer.
- To export a collection of objects to excel file you can use ***ObjectToExcel***  class.
- To import a excel file into a collection you can use ***ExcelToObject*** class.
## Example
> StudentDTO.class
```java
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
    .....
```
>  Export to file 
```java
ByteArrayOutputStream obj  = ObjectToExcel.input(studentDTOS)
                     .forClass(StudentDTO.class)
                     .transfer();
```
> Import to file
```java
List<StudentDTO> datas = ExcelToObject.input(new FileInputStream(file))
                    .forClass(StudentDTO.class)
                    .titleIndex(1)
                    .dataIndex(2)
                    .transfer();
```

### Note
- > This project only support Java 8 later (I strongly recommend you use Java 8 + ).  If you're using Java 7 earlier , you can you this project  [excel-object-mapping](https://github.com/pramoth/excel-object-mapping) (without export module), my project also base on it, special thanks **pramoth**. My project used Maven project structure , you can use maven's command to build and install.# Excel bidirectional transfer to Java Object

## What is it?
This is a small project that will help you interact with excel file and object are easier than. You can export a collection of Objects to Excel file with title, header, basic style and you also can read from Excel file to Object Java with less than code, it bases on Apache Poi.
## How to use?
You need declare two annotations ***@ColumnInfo***,  ***@SheetInfo*** for each class that will be used to transfer.
- To export a collection of objects to excel file you can use ***ObjectToExcel***  class.
- To import a excel file into a collection you can use ***ExcelToObject*** class.
## Example
> StudentDTO.class
```java
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
    .....
```
>  Export to file 
```java
ByteArrayOutputStream obj  = ObjectToExcel.input(studentDTOS)
                     .forClass(StudentDTO.class)
                     .transfer();
```
> Import to file
```java
List<StudentDTO> datas = ExcelToObject.input(new FileInputStream(file))
                    .forClass(StudentDTO.class)
                    .titleIndex(1)
                    .dataIndex(2)
                    .transfer();
```

### Note
> This project only supports Java 8 later (I strongly recommend you use Java 8 + ).  If you're using Java 7 earlier, you can you this project  [excel-object-mapping](https://github.com/pramoth/excel-object-mapping) (without export module), my project also base on it, special thanks **pramoth**. My project used Maven project structure, you can use maven's command to build and install.