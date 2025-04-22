/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package login;

// import SCANNER
import java.util.Scanner;

public class Login {

    //
    public  static Object checkUserName;
    public static Object userDatabase;
    public static String ho_pe;
    public String registeredUsername;
    public String registeredPassword;
    
    // 1st Method
    public static boolean checkUserName(String username){
        if (username.contains("_")&& username.length()<=5){
            return true;
        }else{
            return false;
        }
    }// END of 1st Method
     
        //2nd Method
    /*
    *Title: How can i perform validation on a secure password. Regular expressions on a cgar[]?
    *Author: E.Frisch
    *Date : July 24, 2014
    *Type: Source Code
    *@see https://stackoverflow.com/questions/24924321/how-can-i-perform-validation-on-a-secure-password-regular-expression-on-a-char
    */
    public static boolean checkPasswordComplexity(String password){
        //False statements of the password Complexity
      if (password.length()<8){
        return false;
       }
boolean hasUppercase = false;
boolean hasLowercase = false;
boolean hasDigit = false;
boolean hasSpecialChar = false;
         //Required fileds of the password(What it takes for the password to be true/valid)
         for(char c: password.toCharArray()){
             if(Character.isUpperCase(c)){
                 hasUppercase = true;
             }else if (Character.isLowerCase(c)){
                 hasLowercase = true;
             }else if (Character.isDigit(c)){
                  hasDigit = true;
             }else if ("@!#$%^&*()_+-{}:\"'<>,.?/".indexOf(c)>=0){
                 hasSpecialChar = true ;
             }
            }
           return hasUppercase && hasLowercase && hasDigit && hasSpecialChar;
    }//END of 2nd Method
    
        //3rd Method
    public static boolean checkCellPhoneNumber(String cellphonenumber){
         //Check cellphone number requirements
         boolean countryCode = cellphonenumber.contains("+27");
         boolean length = cellphonenumber.length()==12;
             if(cellphonenumber.contains("+27") && cellphonenumber.length()==12){
                 return true;
             }else{
                 return false;
             }
    }//END of 3rd Method
    
        //4th Method
    public static String registerUser(String username, String password, String cellphonenumber ){
        //Required fields to Register the user
          if(!checkUserName(username)){
              return"Incorrect username,please ensure that username contains an underscore and is not more than 5 characters";
          }else if(!checkPasswordComplexity(password)){
                return"Incorrect password, please ensure that password has atleast 8 characters & comtains uppercase,special characters & digits"; 
          }else if(!checkCellPhoneNumber(cellphonenumber)){
                    return"Ivalid cell phone number.";
                }else{
             return "User registered successfully";
          }
        }//END of 4th Method
            /*
            *save and store the given  userName  amd password in the database.
            *If a Password like this one exist, dont allow creation of the same password
            *If a Password with the same identifier exists,it must be updated.
            *@param username userName to be saved && Password to be saved.
            *@throws DatabaseException if an error occurs during the save operation.
    */
          //Store a username and password for the two Methods
         static String storedUserName = "ho_pe";
         static String storedPassword = "Hope05@$";
          //5th Method Login User ( Validation )
               public static boolean loginUser(String userName,String password){
                   return userName.equals(storedUserName)&& password.equals(storedPassword);
              }//END of Method 5
     
       //Method 6 Login Status of user
    public static String returnLoginStatus(String userName, String password){
          if(!loginUser(userName,password)){
              return"failed to login";
                }else{
                   return"successful login";
          }//END of Method 6
    }
    
       //Returning message  based on the username check
           public static String validateUserNameAndWelcome(String userName, String firstName, String lastName){
            if(checkUserName(userName)){
                return"Welcome" +firstName +lastName + "It is great to see you.";
                }else{
                    return"Username is incorrectly formatted";
                   }
           }

    
    public static void main(String[] args) {
         //Create a Scanner object to read input from the console
        Scanner input = new Scanner(System.in);
        
        //Capture user details
                 System.out.println("Please enter your first name:");
                String firstName = input.nextLine();
               System.out.println("Please enter your last name:");
            String lastName = input.nextLine();
         
         // loop for second chance
         while(true){
             System.out.println("Enter username:");
               String userName = input.nextLine();
                 if(checkUserName(userName)){
                     System.out.println("Username successfully captured");
                        break;
                 }else{
                     System.out.println("Username is incorrectly formatted, please ensure it must have an underscore and must not be more than 5 character long");
                }
               }//
         //Request users password and ensure it is valid
         while(true){
         System.out.println("Create password");
           String password = input.nextLine();
           // ask for a valid password from  user
             if(checkPasswordComplexity(password)){
                 System.out.println("Password correctly captured");
                  break; // break the loop and move on to the next line
                 }else{
                      System.out.println("Password is incorrectly formatted, please ensure that the password contains:"
                              + ";atleast eight characters,Uppercase,digits and special characters");
             } // then continue requesting for the correctly formatted password
         }//
         while(true){
             System.out.println("Enter your rsa cellphone number:"); 
               String cellphonenumber = input.nextLine();
                if(checkCellPhoneNumber(cellphonenumber)){
                    System.out.println("Cellphone number successfully added");
                     break;
                    }else{
                    System.out.println("Incorrectly formatted cellphone number, does not contain rsa code");
                  }
               }//
         //User registration
                //Prompt user for username 
                System.out.println("Enter username:");
                String username = input.nextLine();
                //Prompt user for password
                System.out.println("Enter password:");
                String password = input.nextLine();
                //Prompt user for cellphone number
                System.out.println("Enter your rsa cellphone number");
                String cellphonenumber = input.nextLine();
                //Call RegisterUser method and display the result
                  String result = registerUser(username,password, cellphonenumber);
                 System.out.println(result);
      
           //Attempt to login to users account 
           //Prompt user for username
           System.out.println("Enter username:");
           String inputUsername = input.nextLine();
           //Prompt user for password
           System.out.println("Enter password");
           String inputPassword = input.nextLine();
                    //Feedback for Promptimg correctly or incorrectly
           if(loginUser(inputUsername,inputPassword)){
               System.out.println("Login successful");
           }else{
                System.out.println("Username or password is incorrect");
           }//
           
           //Ask user for username
           System.out.println("Enter username:");
           String userName = input.nextLine();
           
           //Request user for password
           System.out.println("Enter password:");
           String Password = input.nextLine();
           
           //Get Login Status
           String statusMessage = returnLoginStatus(firstName,lastName);
             //Display the result
           System.out.println("Welcome" + statusMessage);
           //end of the Scanner/close the scanner
           
    }
    }
       
   
