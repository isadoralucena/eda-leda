package problems;

/**
 * Classe representando o retorno do metodo pairWise(). Encapsula dois atributos: x e y.
 * @author adalbertocajueiro
 *
 */
public class Pair implements Comparable<Pair>{
	private Integer x;
	private Integer y;
	
	public Pair(Integer x, Integer y) {
		super();
		this.x = x;
		this.y = y;
	}
	public Integer getX() {
		return x;
	}
	public void setX(Integer x) {
		this.x = x;
	}
	public Integer getY() {
		return y;
	}
	public void setY(Integer y) {
		this.y = y;
	}
	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof Pair) {
			result = this.x.equals(((Pair) obj).getX()) &&  this.y.equals(((Pair) obj).getY());
		}
		return result;
	}
	@Override
	public String toString() {
		return "(" + this.x + "," + this.y + ")";
	}

    public boolean equalsWithoutOrder(Object obj) {
		boolean result = false;
		if(obj instanceof Pair) {
			result = this.x.equals(((Pair) obj).getX()) &&  this.y.equals(((Pair) obj).getY())
                || this.x.equals(((Pair) obj).getY()) &&  this.y.equals(((Pair) obj).getX());
		}
		return result;
	}
    @Override
    public int compareTo(Pair o) {
        int result = 0;
        result = this.x - o.x;
        if (result >= 0){
            result = this.y - o.y;
        }
        return result;
    }
}

