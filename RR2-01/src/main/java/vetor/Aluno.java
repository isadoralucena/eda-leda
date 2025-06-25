package vetor;

public class Aluno implements Comparable<Aluno> {
	private String nome;
	private double media;

	public Aluno(String nome, double media) {
		super();
		this.nome = nome;
		this.media = media;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getMedia() {
		return media;
	}

	public void setMedia(double media) {
		this.media = media;
	}

	@Override
	public int compareTo(Aluno o) {
		return Double.compare(this.media, o.media);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome, media);
	}

	@Override
	public boolean equals(Object obj) {
		 Aluno other = (obj instanceof Aluno) ? (Aluno) obj : null;
		return other != null &&
			Double.compare(media, other.media) == 0 &&
			nome.equals(other.nome);
	}
}
