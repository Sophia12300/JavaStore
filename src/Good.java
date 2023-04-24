import java.io.Serializable;
import java.math.BigDecimal;
public class Good implements Comparable<Good>,Cloneable,Serializable{
    //商品编号
    private int id;
    //商品名称
    private String name;
    //商品价格
    private BigDecimal price;
    //商品数量
    private int num;

    public Good() {
        super();
    }
    public Good(int id, String name, BigDecimal price, int num) {
        super();
        this.id = id;
        this.name = name;
        this.price = price;
        this.num = num;
    }

    @Override
    protected Good clone() throws CloneNotSupportedException {
        Good good = (Good) super.clone();
        return good;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }
    @Override
    public String toString() {
        return "Good [id=" + id + ", name=" + name + ", price=" + price + ", num=" + num + "]";
    }
    @Override
    public int compareTo(Good good) {
        return this.price.compareTo(good.price);
    }
}
