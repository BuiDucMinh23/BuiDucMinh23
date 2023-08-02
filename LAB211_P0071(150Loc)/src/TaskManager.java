
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/*
 * To change taskList license header, choose License Headers in Project Properties.
 * To change taskList template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 *p6
 */
public class TaskManager extends ArrayList<Task> {

    final GetData getdata = new GetData();
    final Display display = new Display();

    int loadfromfile(ArrayList<Task> TaskList) throws FileNotFoundException {
        int idSave = 0;
        File file = new File("datasave.txt");
        //check file is exist or not
        if (!file.exists()) {
            System.out.println("File isn't exists");

        } else {

            Scanner sc = new Scanner(file);
            
            //this loop run from the fisrt line and stop when find end of file
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] fields = line.split("/");

                int id = Integer.parseInt(fields[0]);
                String taskType = fields[1];
                String requirementName = fields[2];
                String date = fields[3];
                double planFrom = Double.parseDouble(fields[4]);
                double planTo = Double.parseDouble(fields[5]);
                String Assignee = fields[6];
                String reviewer = fields[7];
                Task task = new Task(id, taskType, requirementName, date, planFrom, planTo, Assignee, reviewer);
                TaskList.add(task);
                idSave = id;
            }
            
        }
        
        return idSave;
    }
    int loadfileID() throws FileNotFoundException{
        
        File file = new File("IDsave.txt");
        //check file is exist or not
        
            Scanner sc = new Scanner(file);
            
            int idSave=Integer.parseInt(sc.nextLine());
            
         
        return idSave;
    }
    public int addTask(ArrayList<Task> TaskList) throws FileNotFoundException, IOException {

        System.out.println("----------------Add Task------------------");
        
        int idSave=loadfileID()+1;
        
        
        
        String requirementName = getdata.getString("Requirement Name: ", "", "");
        String taskType = getdata.GetTaskType("Task Type: ");
        String date = getdata.getDate("Date: ");
        double planFrom = getdata.getDouble("From: ", "Plan From must be within 8h-17h", 8.0, 17.0);
        double planTo = getdata.getDouble("To: ", "Plan To must be within From to 17h30", planFrom + 0.5, 17.5);
        String Assignee = getdata.getString("Assignee: ", "", "");
        String reviewer = getdata.getString("Reviewer: ", "", "");
        boolean isExist = checkDuplicate(date, Assignee, planFrom, planTo, TaskList);
        //check value of variable isExist
        if (isExist) {
            System.out.println("Duplicate!!!");
        } else {
            Task newTask = new Task(idSave, taskType, requirementName, date, planFrom, planTo, Assignee, reviewer);
            TaskList.add(newTask);
            idSave++;
            System.out.println("Add successful!!");
        }
        savefileID(idSave);
        return idSave;
    }

    boolean checkDuplicate(String date, String assignee, double planFrom, double planTo, ArrayList<Task> TaskList) {
        boolean isExist = false;
        //loop use to access each element of arraylist from begining to the end
        for (Task task : TaskList) {
            //compare date in list with date input and assignee in list and assignee input
            if (date.equals(task.getDate()) && assignee.equals(task.getAssignee())) {
                //compare planTo and planfrom with planFrom and planto in every task in list 
                //have date and assignee same with date, assignee input
                /*planto of object newtask must be lest than planFrom in list
                or planfrom of object newtask must be large than planto in list */
                if ((planTo < task.getPlanFrom()) || (planFrom > task.getPlanTo())) {
                    isExist = false;
                } else {
                    isExist = true;
                    break;
                }
            }
        }
        return isExist;
    }

    void deleteTask(ArrayList<Task> taskList) {
        if (taskList.isEmpty()) {
            System.out.println("List task is empty!");
            return;
        } else {
            System.out.println("-------Del Task---------");
            int indexInList = -1;
            int TaskId = getdata.getInt("ID: ", "Task id is out of range!", 1, Integer.MAX_VALUE);
            //loop use to access each element of arraylist from begining to the end
            for (Task task : taskList) {
                //compare variable taskid with every taskId has in list
                if (TaskId == task.getTaskID()) {
                    indexInList = taskList.indexOf(task);
                }
            }
            //check indexInList not equal - 1 or not
            if (indexInList != -1) {
                taskList.remove(indexInList);
                System.out.println("Delete successful!");
            } else {
                System.out.println("Id is not exist!");
            }

        }

    }

    public void display(ArrayList<Task> taskList) {
        if (taskList.isEmpty()) {
            System.out.println("List task is empty!");
            return;
        } else {
            System.out.println("----------------------------Task-------------------------------------");
            System.out.format("%-7s%-20s%-12s%-15s%-7s%-15s%-15s\n",
                    "Id", "Name", "Task Type", "Date", "Time", "Assignee", "Reviewer");
            //loop use to access each element of arraylist from begining to the end
            for (Task task : taskList) {
                System.out.println(task);
            }
        }
    }

    void savetofile(ArrayList<Task> TaskList) throws FileNotFoundException, IOException {
        if (TaskList.isEmpty()) {
            System.out.println("");

        } else{
        FileWriter file = new FileWriter("datasave.txt");
        //this loop run from first of list to end of list
        for (Task task : TaskList) {
            file.write(task.getTaskID() + "/" + task.getRequirementName() + "/" + task.getTaskTypeID() + "/" + task.getDate() + "/"
                    + task.getPlanFrom() + "/" + task.getPlanTo() + "/" + task.getAssignee() + "/" + task.getReviewer() + '\n');
        }

        file.close();
    }
    }
    void savefileID(int id) throws IOException{
        FileWriter file = new FileWriter("IDsave.txt");
        file.write(String.valueOf(id));
        file.close();
    }
}
