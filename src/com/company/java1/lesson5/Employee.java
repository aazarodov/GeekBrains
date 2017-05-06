package com.company.java1.lesson5;

/** класс "Сотрудник"
 *  с полями: ФИО, должность, email, телефон, зарплата, возраст
 */
public class Employee {
    String fio;
    String post;
    String email;
    String telephone;
    float salary;
    int age;

    public Employee() {
    }

    /** Конструктор класса должен заполнять эти поля при создании объекта;
     * @param fio
     * @param post
     * @param email
     * @param telephone
     * @param salary
     * @param age
     */
    public Employee(String fio, String post, String email, String telephone, float salary, int age) {
        this.fio = fio;
        this.post = post;
        this.email = email;
        this.telephone = telephone;
        this.salary = salary;
        this.age = age;
    }

    /** Внутри класса «Сотрудник» написать метод, который выводит информацию об объекте в консоль
     * @return описание объекта класса "Сотрудник"
     */
    @Override
    public String toString() {
        return "Employee{" +
                "fio='" + fio + '\'' +
                ", post='" + post + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                '}';
    }

    /** Геттер, возвращающий возврат
     * @return возраст
     */
    public int getAge() {
        return age;
    }

    /** Создать массив из 5 сотрудников
     * @return массив из 5 сотрудников
     */
    public static Employee[] getEmployeeArray() {
        Employee[] emplArray = new Employee[5];
        emplArray[0] = new Employee("Ivanov Ivan", "Engineer", "ivivan@mailbox.com", "892312312", 30000, 30); // потом для каждой ячейки массива задаем объект
        emplArray[1] = new Employee("Petrov Peter", "Project manager", "petrov@mailbox.com", "89991231212", 60000, 45);
        emplArray[2] = new Employee("Kuprin Ivan", "Bussiness analitic", "kuprin@mailbox.com", "89994443322", 40000, 37);
        emplArray[3] = new Employee("Kuzmin Egor", "Tester", "kuzmin@mailbox.com", "89007778812", 35000, 45);
        emplArray[4] = new Employee("Legus Vilgelm", "Developer", "legus@mailbox.com", "89214441212", 40000, 37);
        return emplArray;
    }

    /** С помощью цикла вывести информацию только о сотрудниках старше 40 лет
     * @param emplArray
     */
    public static void printEmployeeOver60(Employee[] emplArray) {
        for (Employee empl : emplArray) {
            if (empl.getAge() > 40)
                System.out.println(empl);
        }
    }
}
