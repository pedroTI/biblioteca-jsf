package br.com.biblioteca.model.livro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@NamedQueries({
		@NamedQuery(name = "Livro.findAll", query = "SELECT o FROM Livro o"),
		@NamedQuery(name = "Livro.count", query = "SELECT COUNT(o) FROM Livro o"),
		@NamedQuery(name = "Livro.findById", query = "SELECT o FROM Livro o WHERE o.id=:id"),
		@NamedQuery(name = "Livro.ItemLivro.count", query = "SELECT COUNT(o.itens) FROM Livro o WHERE o.id=:id"),
		@NamedQuery(name = "Livro.ItemLivro.findAll", query = "SELECT o.itens FROM Livro o WHERE o.id=:id"),
		@NamedQuery(name = "Livro.Join.Editora.Autor.Assunto.ItemLivro", query = "SELECT o FROM Livro o "
				+ "LEFT JOIN FETCH o.editora "
				+ "LEFT JOIN FETCH o.autor "
				+ "LEFT JOIN FETCH o.assunto "
				+ "LEFT JOIN FETCH o.itens "
				+ "WHERE o.id=:id") })
public class Livro implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	private Editora editora = new Editora();
	private String titulo;
	@ManyToOne
	private Autor autor = new Autor();
	@ManyToOne
	private Assunto assunto = new Assunto();
	@OneToMany(mappedBy = "livro", cascade = { CascadeType.PERSIST,
			CascadeType.REFRESH, CascadeType.MERGE })
	private List<ItemLivro> itens = new ArrayList<ItemLivro>();

	public Livro() {
		super();
	}

	public Livro(Editora editora, String titulo, Autor autor, Assunto assunto) {
		super();
		this.editora = editora;
		this.titulo = titulo;
		this.autor = autor;
		this.assunto = assunto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<ItemLivro> getItens() {
		return itens;
	}

	public void setItens(List<ItemLivro> itens) {
		this.itens = itens;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public Assunto getAssunto() {
		return assunto;
	}

	public void setAssunto(Assunto assunto) {
		this.assunto = assunto;
	}

	public boolean getExistemItensCadastrados() {
		if (this.getId() != null) {
			if (this.getItens().size() > 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "Livro [id=" + this.id + ", titulo=" + this.titulo + "]";
	}

}
