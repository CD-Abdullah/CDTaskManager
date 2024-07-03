public class Tasks {
    public int UID;
    public String TTitle;
    public String TDesc;

    Tasks(){
        UID = 0;
        TTitle=null;
        TDesc=null;
    }

    public Tasks AddTasks(int loggedInUser){
        Tasks tasks = new Tasks();

        System.out.print("Enter Title: ");
        tasks.TTitle = GetInput.getString();

        tasks.UID = loggedInUser;

        System.out.print("Enter Description: ");
        tasks.TDesc = GetInput.getString();

        return tasks;
    }
}
