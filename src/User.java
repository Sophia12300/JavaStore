import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;

public class User implements Serializable {
    private String username;
    private String password;
    public static boolean isLogin;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLogin() {
		return isLogin;
	}
	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

    //注册
    public void reg() {
        String username = "";
        String password = "";
        while(true) {
            System.out.println("请输入用户名:");
            //调用Scanner类对象sc的方法从输入流中获取你需要的输入,sc.next:遇到第一个扫描有效字符，即第一个非空格非换行符后面开始，
            //一直获取到下一个空格/换行符之前的，单个字符串
            username = Shop.sc.next();
            boolean flag = true;
            //判断用户名是否存在
            for (User user : Shop.userList) {
                if(username.equals(user.getUsername())) {
                    System.out.println("用户名已存在！");
                    flag=false;
                    break;
                }
            }

            if(flag==false) {
                continue;
            }
            //用户名长度不能少于3位
            if(username.length()<3) {
                System.out.println("用户名长度不能少于3位！");
                continue;
            }else {
                break;
            }

        }

        while(true) {
            System.out.println("请输入密码:");
            password = Shop.sc.next();

            //密码长度不能少于6位
            if(password.length()<6) {
                System.out.println("密码长度不能少于6位！");
                continue;
            }

            char ch;
            int digit = 0;
            int letter = 0;
            for(int i=0; i<password.length(); i++) {
                //取得密码中的每个字符(public char charAt(int index):返回处于指定索引位置的字符，index的范围是[0,s.length()-1])
                ch = password.charAt(i);
                //计算密码中数字字符的个数(Character.isDigit()方法:用于判断指定字符是否为数字)
                if(Character.isDigit(ch)) {
                    digit = digit +1;
                }
                //计算密码中字母字符的个数(Character.isLetter()方法:判断指定字符是否为字母)
                if(Character.isLetter(ch)) {
                    letter = letter + 1;
                }
            }
            if(digit == 0 || letter == 0) {
                System.out.println("密码不能全为字母或全为数字！");
                digit = 0;
                letter = 0;
                //结束本次循环。即本次循环中continue后面的代码不执行，进行下一次循环的入口判断
                continue;
            }

            System.out.println("请再次确认密码:");
            String repassword = Shop.sc.next();
            //两次密码输入一致
            if(password.equals(repassword)) {
                //将赋值后的用户对象放入用户集合
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                Shop.userList.add(user);
                //将注册的用户信息保存到文件
//				Shop.saveListToFile();

                //永久终止循环。即不执行本次循环中break后面的语句，并且直接跳出循环
                break;
            }else {
                System.out.println("两次密码输入不一致！");
            }
        }
        System.out.println("注册成功！");
    }

    //登录
    public void login() {
        String username = "";
        String password = "";
        boolean login_flag=false;

        System.out.println("请输入用户名：");
        while (true) {
            username = Shop.sc.next();
            System.out.println("请输入密码：");
            password = Shop.sc.next();

            //判断输入的用户名和密码是否正确
            for (User user : Shop.userList) {
                if(username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                    System.out.println("登录成功！");
                    isLogin = true;
                    login_flag = true;
                    break;
                }
            }

            if(login_flag == true) {
                break;
            }else {
                System.out.println("登录失败,请重新登录！");
                System.out.println("请重新输入用户名：");
            }
        }
    }

    //查看商品列表
    public void showGoodList(){
        System.out.println("******商品列表如下*********");
        //对商品排序
        Collections.sort(Shop.goodList);
        for (Good good : Shop.goodList) {
            System.out.println(good);
        }
    }

    //查看购买的商品列表
    public void showMyGoodList() {
        System.out.println("**********您购买的商品列表如下*********");
        //总价格
        BigDecimal total = new BigDecimal("0");
        for (Good good : Shop.myGoodList) {
            System.out.println(good);
            //计算商品总价格
            BigDecimal price = good.getPrice();
            int num = good.getNum();
            total = total.add(price.multiply(BigDecimal.valueOf(num)));
        }
        System.out.println("总价格为："+total);
    }

    //购买商品
    public void buy(){
        System.out.println("请输入您要购买的商品编号：");
        Good good = null;
        while(true) {
            int id=Shop.sc.nextInt();
            //根据商品编号查找商品信息
            good = findGoodById(id);
            if(good == null) {
                System.out.println("未找到该商品!");
                System.out.println("请重新输入您要购买的商品编号：");
            }else {
                System.out.println("您将要购买的商品信息如下：");
                System.out.println(good);
                break;
            }
        }
        System.out.println("请输入您要购买的商品数量：");
        while(true) {
            int num = Shop.sc.nextInt();
            //判断购买的商品数量是否大于库存数量
            if(num > good.getNum()) {
                System.out.println("库存不足!");
                System.out.println("请重新输入您要购买的商品数量：");
            }else {
                is_continue = sc.next();
                if(is_continue.equals("Y")||is_continue.equals("y")){
                    System.out.println("是否继续购买：Y/N");
                    //购买商品
                    System.out.println("*******商品购买成功！********");
                    //查看购买的商品列表
                    user.showMyGoodList();
                }
                System.out.println("用户取消购买。");
                Good myGood = new Good();
                try {
                    myGood = good.clone();
                    myGood.setNum(num);
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }

                //把已购商品添加到集合中
                Shop.myGoodList.add(myGood);

                //更新商品的库存数量
                int newNum = good.getNum() - num;
                good.setNum(newNum);
                break;
            }
        }

    }

    //根据商品编号查找商品信息
    public Good findGoodById(int id) {
        Good returnGood = null;
        for (Good good : Shop.goodList) {
            if(good.getId() == id) {
                returnGood = good;
                break;
            }
        }
        return returnGood;
    }

}
