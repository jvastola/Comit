import java.sql.*;
import java.util.Scanner;

public class project {
    int uid = -1;
    static Connection conn;
    static Statement stat;
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        connect();
        int action = -1;
        while (action != 0) {
            System.out.print("Enter number for action:\n 1. Login \n 2. Create account \n 0. Disconnect\n\nChoice: ");
            action = input.nextInt();
            if (action == 1) {
                login();
            }
            if (action == 2) {
                createUser();
            }
        }
        conn.close();
        input.close();
        stat.close();
    }
    public static void connect() throws SQLException {
        conn = DriverManager.getConnection("jdbc:sqlite:comit.db");
        stat = conn.createStatement();
        System.out.println("Welcome to Comit!");

    }
    public static void login() throws SQLException {
        ResultSet rs;
        PreparedStatement pstmt;
            System.out.print("Enter Username:Password ");
            String userinfo = input.next();

            pstmt = conn.prepareStatement("SELECT u_name, FROM user WHERE u_login = ?");
            pstmt.setString(1, userinfo);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("Signed in as : "+rs.getString("u_name") );
                uid=u_rs.getInt("u_id");
            }
            loggedin();
    }

    public static void createUser() throws SQLException {
        ResultSet rs;
        PreparedStatement pstmt;
            System.out.print("Enter Username: ");
            String username = input.next();

            System.out.print("Enter Password: ");
            String password = input.next();

        //create new id
        int userkey = -1;
        rs = stat.executeQuery("SELECT MAX(u_id) FROM user;");
        while (rs.next()) {
            userkey = rs.getInt("MAX(u_id)");
        }
        userkey++;
        String userinfo = username + ":" + password;
        pstmt = conn.prepareStatement("INSERT INTO user VALUES (?, ?, ?)");
        pstmt.setInt(1, username);
        pstmt.setString(2, uid);
        pstmt.setString(3, userinfo);
        pstmt.executeUpdate();

            pstmt = conn.prepareStatement("SELECT u_name, FROM user WHERE u_login = ?");
            pstmt.setString(1, userinfo);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("Signed in as : "+rs.getString("u_name") );
                uid=u_rs.getInt("u_id");
            }
        rs.close();
        pstmt.close();
        loggedin();
    }
    public static void loggedin() throws SQLException {
        int action = -1;
        while (action != 0) {
            System.out.println("\nWhat would you like to do?");

            System.out.println("1. View a user projects");
            System.out.println("2. Manage/Create Project");
            System.out.println("3. Make Edit");
            System.out.println("4. Manage groups");
            System.out.println("5. View user commits");
            System.out.println("0. Return\n\nChoice:");
                action = input.nextInt();

            if (action == 1) {
                userprojects();
            }
            if (action == 2) {
                projectmanager();
            }
            if (action == 3) {
                newedit();
            }
            if (action == 4) {
                managegroups();
            }
            if (action == 5) {
                viewusercommits();
            }
        }
    }

    public static void userprojects() throws SQLException {
        ResultSet rs;
        PreparedStatement pstmt;
        String viewid = " ";
        System.out.println("\nEnter User id:");
        viewid = input.nextInt();

        int groupid = -1;
        pstmt = conn.prepareStatement("SELECT d_gid FROM developer WHERE d_did = ?;");
        pstmt.setString(1, viewid);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            groupid = rs.getInt("d_gid");
        }

        pstmt = conn.prepareStatement("SELECT p_name, FROM project WHERE p_did = ?");
            pstmt.setString(1, viewid);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("User Project: "+rs.getString("p_name") );
            }
        pstmt = conn.prepareStatement("SELECT p_name, FROM project WHERE p_gid = ?");
            pstmt.setString(1, groupid);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("Group Project: "+rs.getString("p_name") );
            }
        rs.close();
    }

    public static void projectmanager() throws SQLException {
        ResultSet rs;

PreparedStatement pstmt;
        int action = -1;
        while (action !=0){
             System.out.println("\n1. Create Project");
             System.out.println("2. Manage Project");
             System.out.println("0. Return\n\nChoice:");
            action = input.nextInt();
            if(action = 1){
                System.out.print("Enter Project Name: ");
                String pname = input.next();

                System.out.print("Group Project: 1=No 2=Yes ");
                int gproject = input.nextInt();//doesnt work currently


            //create new project id
            int projectkey = -1;
            rs = stat.executeQuery("SELECT MAX(p_pid) FROM project;");
            while (rs.next()) {
                projectkey = rs.getInt("MAX(p_pid)");
            }
            projectkey++;

            pstmt = conn.prepareStatement("INSERT INTO project VALUES (?, ?, ?,?)");
            pstmt.setInt(1, projectkey);
            pstmt.setString(2, pname);
            pstmt.setString(4, u_id);
            pstmt.executeUpdate();
 System.out.println("Project Created : "+pname );
            rs.close();
            }
             if(action = 2){
                //ToDo
            }
        }
    }
    public static void newedit() throws SQLException {
        ResultSet rs;
        PreparedStatement pstmt;

        System.out.println("Enter type: Insert, Delete " );
        String etype = input.next();
        System.out.println("Enter Filename:" );
        String filename = input.next();
        System.out.println("Enter Content" );
        String content = input.next();
        int eid=-1;
        rs = stat.executeQuery("SELECT MAX(e_id) FROM edit;");
            while (rs.next()) {
                eid = rs.getInt("MAX(e_id)");
            }
            eid++;

        pstmt = conn.prepareStatement("INSERT INTO edit VALUES (?, ?, ?,?,?,?,?)");
            pstmt.setInt(1, eid);
            pstmt.setString(2, etype);
            pstmt.setString(3, 1);

            pstmt.setString(4, content);
            pstmt.setString(5, -1);
            pstmt.setString(6, filename);
            pstmt.setString(7, u_id);
            pstmt.executeUpdate();


        pstmt.close();
        rs.close();
    }

    public static void managegroups() throws SQLException {
        ResultSet rs;
        PreparedStatement pstmt;

        int action = -1;
        while (action != 0) {
            System.out.println("\nWhat would you like to do?");

            System.out.println("1. Create new Group");
            System.out.println("2. Remove Someone from your group");
            System.out.println("3. Add Group members");
            System.out.println("4. Delete your Group");
            System.out.println("0. Return\n\nChoice:");
            action = input.nextInt();

            if (action == 1) {
                    System.out.println("Enter Group Name:");
                    String gname= input.next();
                    int gid=-1;
                    rs = stat.executeQuery("SELECT MAX(e_id) FROM edit;");
                     while (rs.next()) {
                        eid = rs.getInt("MAX(e_id)");
                    }
                    gid++;

                    pstmt = conn.prepareStatement("INSERT INTO groups VALUES (?, ?, ?)");
                    pstmt.setInt(1, gid);
                    pstmt.setString(2, gname);
                    pstmt.setInt(3, u_id);
                    pstmt.executeUpdate();

            }
            if (action == 2) {
                System.out.println("Enter User to remove:");
                    int uremove= input.nextInt();
                System.out.println("Enter Group to remove:");
                    String groupname= input.next();


                     pstmt = conn.prepareStatement("SELECT g_leaderid,g_name FROM groups WHERE g_leaderid= ? and g_name = ?;");
                    pstmt.setInt(1, u_id);
                    pstmt.setString(2, gname);

                    rs = pstmt.executeQuery();
                    int leader=-1;
                    while (rs.next()) {
                        leader = rs.getInt("g_leaderid");

                    }
                if(leader ==u_id){
                    pstmt = conn.prepareStatement("DELETE FROM developer,groups WHERE d_did = ? and g_gid=d_gid ");
                    pstmt.setInt(1, uremove);
                    pstmt.executeUpdate();
                }
                else{
                    System.out.println("Invalid Permission:");
                }

            }
            if (action == 3) {
                System.out.println("Enter user id to add:");
                   int uadd= input.nextInt();
                //Finish
            }
            if (action == 4) {
                //delete group
            }
        }

        pstmt.close();
        rs.close();
    }

    public static void viewusercommits() throws SQLException {
        ResultSet rs;
        PreparedStatement pstmt;

        System.out.println("Enter user id:");
                   int userid= input.nextInt();

                     pstmt = conn.prepareStatement("SELECT c_cid,c_comment,c_comtime FROM comit WHERE c_uid=?;");
                    pstmt.setInt(1,userid);
                    rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("c_cid")+ ":"+rs.getString("c_comment")+ ":"+ rs.getString("c_comtime") );
            }

        pstmt.close();
        rs.close();
    }
}
