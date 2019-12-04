package main.backend;

public class IdNumberStudent extends IdNumber{
    public IdNumberStudent() {
        super();
    }

    public IdNumberStudent(String studentId) {
        super(studentId);
    }

    /**
     * 9 digit code U12345678
     */
    //check function
    public static boolean isValidStudentId(String studentId) {
        if(studentId.length() != 9 && studentId.charAt(0) != 'U'){
            return false;
        } else {
            for (int i = 1; i < studentId.length(); i++){
                if(!Character.isDigit(studentId.charAt(i))) return false;
            }
        }
        return true;
    }
}
