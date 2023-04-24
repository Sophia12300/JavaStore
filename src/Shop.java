
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Shop {

    //Scanner类可以获取用户从控制台输入的信息。
    //使用方法：①首先需要构造一个Scanner类的对象，并且与标准输入流System.in关联
    //②调用Scanner类对象sc的方法从输入流中获取你需要的输入：sc.nextInt()
    //当创建了一个Scanner类对象之后，控制台会一直等待输入，直到敲回车键结束，把所输入的内容传给对象sc，若要获取需要的内容，调用sc的方法即可
    static Scanner sc = new Scanner(System.in);
    //ArrayList是List接口的实现类（将一个子类对象直接赋值给一个父类引用变量，这是多态的体现）
    static List<User> userList = new ArrayList<User>();
    static List<Good> goodList = new ArrayList<Good>();
    static List<Good> myGoodList = new ArrayList<Good>();
    static File userFile = new File("user");
    static File goodFile = new File("good");

    public static void main(String[] args) {

        Shop shop = new Shop();
        shop.initGoodList();
//		shop.readListFromFile();
        //菜单循环显示flag
        boolean go_on = true;
        //菜单循环显示
        while(go_on) {
            int choice = shop.showMainMenu();
            go_on = shop.chooseMenu(choice,go_on);
        }
    }

    //初始化商品列表
    private void initGoodList() {
        readListFromFile();
        Good good1 = new Good(1,"海尔冰箱",BigDecimal.valueOf(1999),100);
        Good good2=new Good(2,"海信电视",BigDecimal.valueOf(4999),100);
        Good good3=new Good(3,"小米手机",BigDecimal.valueOf(999),100);
        goodList.add(good1);
        goodList.add(good2);
        goodList.add(good3);
    }

    //菜单显示
    private int showMainMenu() {
        System.out.println("*****欢迎进入电子商城*****");
        System.out.println("\t1.注册");// \t：制表符，相当于tab
        System.out.println("\t2.登录");
        System.out.println("\t3.查看商城");
        System.out.println("\t4.查看我购买的商品");
        System.out.println("\t5.管理员登录");
        System.out.println("\t6.退出系统");
        System.out.println("***********************");
        System.out.print("请选择菜单：");
        //选择的菜单(调用Scanner类对象sc的方法从输入流中获取用户的输入:nextInt()只读取整数)
        int choice = sc.nextInt();
        return choice;
    }

    //选择菜单
    private boolean chooseMenu(int choice,boolean go_on) {
        User user = new User();
        switch (choice) {
            case 1:
                System.out.println("您选择的菜单是:注册");
                user.reg();
                break;
            case 2:
                System.out.println("您选择的菜单是:登录");
                user.login();
                break;
            case 3:
                System.out.println("您选择的菜单是：查看商城");
                user.showGoodList();
                //判断用户是否登录，已确定是购买还是提示登录
                if(User.isLogin) {//在User类中定义静态成员变量isLogin，在登录方法中，登录成功后设置为true
                    String is_continue = "Y";
                    while("Y".equals(is_continue) || "y".equals(is_continue)) {
                        //购买商品
                        user.buy();
                        System.out.println("是否继续购买：Y/N");
                        is_continue = sc.next();
                    }
                    System.out.println("*******商品购买成功！********");
                    //查看购买的商品列表
                    user.showMyGoodList();
                }else{
                    System.out.println("您还未登录，请先登录，再购买商品");
                }
                break;
            case 4:
                System.out.println("您选择的菜单是：查看我购买的商品");
                user.showMyGoodList();
                break;
            case 5:
                System.out.println("您选择的菜单是:管理员登录");
                Admin admin = new Admin();
                admin.adminLogin();
                break;
            case 6:
                System.out.println("谢谢使用!");
                go_on = false;
                //程序立即停止(System.exit()方法是停止虚拟机运行)
                System.exit(0);
                break;
            default:
                System.out.println("您的输入有误！");
                break;
        }

        return go_on;
    }

    public static void saveListToFile(){
        try {
            FileOutputStream fos = new FileOutputStream(userFile);
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            //把集合中的内容保存到文件中
            oos.writeObject(userList);
            fos.close();
            oos.close();

            FileOutputStream fos1 = new FileOutputStream(goodFile);
            ObjectOutputStream oos1=new ObjectOutputStream(fos1);
            oos1.writeObject(goodList);
            fos1.close();
            oos1.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //
    public void readListFromFile(){
        try {
            FileInputStream fis= new FileInputStream(userFile);
            ObjectInputStream ois=new ObjectInputStream(fis);
            userList=(ArrayList<User>)ois.readObject();
            ois.close();
            fis.close();

            FileInputStream fis1= new FileInputStream(goodFile);
            ObjectInputStream ois1=new ObjectInputStream(fis1);
            goodList=(ArrayList<Good>)ois1.readObject();
            ois1.close();
            fis1.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}