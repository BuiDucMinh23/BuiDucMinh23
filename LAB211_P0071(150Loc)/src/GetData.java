
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *

 */
public class GetData {

    Scanner sc = new Scanner(System.in);

    int getInt(String msg, String outRangeMsg, int min, int max) {
        String input;
        int result;
        do {
            System.out.print(msg);
            input = sc.nextLine();
            if (input.isEmpty()) {
                System.out.println("Input could not be empty!!!");
            } else {
                try {
                    result = Integer.parseInt(input);
                    //check choice in range min and max
                    if (result >= min && result <= max) {
                        break;
                    } else {
                        System.out.println(outRangeMsg);
                        continue;
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Input must be integer");
                }
            }
        } while (true);
        return result;
    }

    String getString(String msg, String formatMsg, String regex) {
        String input;
        do {
            System.out.print(msg);
            input = sc.nextLine();
            if (input.isEmpty()) {
                System.out.println("Input could not be empty!!!");
                continue;
            } else {
                if (regex.isEmpty()) {
                    break;
                    //compare input with regex
                } else if (input.matches(regex)) {
                    break;
                } else {
                    System.out.println(formatMsg);
                    continue;
                }
            }
        } while (true);
        return input;
    }
    static String getPreviousDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1); // Lấy ngày trước đó

        Date previousDate = calendar.getTime();

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(previousDate);
    }
    String getDate(String msg) {
        String input;
        Date date;
        String resultDate;
        
        do {
            System.out.print(msg);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            dateFormat.setLenient(false);
            input = sc.nextLine();
            if (input.isEmpty()) {
                System.out.println("Input could not be empty!!!");
                continue;
            } // \d{1,2}: the number have 1 or 2 digit number
            //[-]: contain character -
            //\d{4}: the number must have 4 digit
            else if (!input.matches("\\d{1,2}[-]\\d{1,2}[-]\\d{4}")) {
                System.out.println("Input is wrong format");
                continue;
            }
            try {
                date = dateFormat.parse(input);
                Date now = new Date();
                //String datebefore=getPreviousDate();
                //check date must be before now
                if(dateFormat.format(date).equals(dateFormat.format(now))){
                    break;
                }
                else if (date.before(now)) {
                    System.out.println("Date could not be the past. Please enter again!");
                    continue;
                }else
                break;
            } catch (ParseException exception) {
                System.out.println("Date doesn't existed!!");
            }
        } while (true);
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        resultDate = dateFormat.format(date);
        return resultDate;
    }

    String GetTaskType(String msg) {
        int tasktypeID = getInt(msg, "Task Type must be in range[1-4]", 1, 4);
        String result = "";
        switch (tasktypeID) {
            case 1:
                result = "Code";
                break;
            case 2:
                result = "Test";
                break;
            case 3:
                result = "Design";
                break;
            case 4:
                result = "Review";
                break;
        }
        return result;
    }
    String convertToTime(double number) {
        int hours = (int) number;
        int minutes = (int) ((number - hours) * 60);
        return String.format("%02d:%02d", hours, minutes);
    }
    double getDouble(String msg, String outRangeMsg, double min, double max) {
        Scanner sc = new Scanner(System.in);
        double result;
        String input;
        
        do {
            input = getString(msg, "Input must be a number in the format x.0 or x.5", "^(\\d+\\.([5]|[0]))$");
            
            if (input.isEmpty()) {
                System.out.println("Input could not be empty!");
            } else {
                try {
                    result = Double.parseDouble(input);
                    
                    if (result >= min && result <= max) {
                        // Check if the input time is in the future
                        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                        Date currentTime = new Date();
                        String currentTimeString = timeFormat.format(currentTime);
                        String inputTimeString = convertToTime(result);
                        
                        if (timeFormat.parse(inputTimeString).after(timeFormat.parse(currentTimeString))) {
                            break; // Input time is in the future
                        } else {
                            System.out.println("Time must be in the future. Please enter again!");
                            continue;
                        }
                    } else {
                        System.out.println(outRangeMsg);
                        continue;
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Input must be a valid number!");
                } catch (ParseException ex) {
                    System.out.println("Invalid time entered!");
                }
            }
        } while (true);
        
        return result;
    }    
}
