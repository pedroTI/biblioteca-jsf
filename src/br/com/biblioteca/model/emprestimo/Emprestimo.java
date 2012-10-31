package br.com.biblioteca.model.emprestimo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import br.com.biblioteca.model.livro.ItemLivro;
import br.com.biblioteca.model.pessoa.Locador;

@Entity
@NamedQueries({
		@NamedQuery(name = "Emprestimo.findAll", query = "SELECT o FROM Emprestimo o"),
		@NamedQuery(name = "Emprestimo.count", query = "SELECT COUNT(o) FROM Emprestimo o"),
		@NamedQuery(name = "Emprestimo.findById", query = "SELECT o FROM Emprestimo o WHERE o.id=:id"),
		@NamedQuery(name = "Emprestimo.Livro.count", query = "SELECT COUNT(o.livros) FROM Emprestimo o WHERE o.id=:id"),
		@NamedQuery(name = "Emprestimo.Livro.findAll", query = "SELECT o.livros FROM Emprestimo o WHERE o.id=:id"), })
public class Emprestimo implements Serializable {
	@SuppressWarnings("unused")
	private static final int diasLimite = 12;
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long id;
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH,
			CascadeType.MERGE, CascadeType.DETACH })
	private Locador locador = new Locador();
	@Temporal(TemporalType.DATE)
	private Date dataEmprestimo;
	@Temporal(TemporalType.DATE)
	private Date dataDevolucaoEsperada;
	@Temporal(TemporalType.DATE)
	@Column(nullable = true)
	private Date dataDevolucao;
	@ManyToMany(mappedBy = "emprestimos", cascade = { CascadeType.PERSIST,
			CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH })
	private List<ItemLivro> livros = new ArrayList<ItemLivro>();

	public Emprestimo() {
		super();
	}

	public Emprestimo(Locador locador, Date dataEmprestimo,
			Date dataDevolucaoEsperada, Date dataDevolucao,
			List<ItemLivro> livros) {
		super();
		this.locador = locador;
		this.dataEmprestimo = dataEmprestimo;
		this.dataDevolucaoEsperada = dataDevolucaoEsperada;
		this.dataDevolucao = dataDevolucao;
		this.livros = livros;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Locador getLocador() {
		return locador;
	}

	public void setLocador(Locador locador) {
		this.locador = locador;
	}

	public Date getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(Date dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public Date getDataDevolucaoEsperada() {
		return dataDevolucaoEsperada;
	}

	public void setDataDevolucaoEsperada(Date dataDevolucaoEsperada) {
		this.dataDevolucaoEsperada = dataDevolucaoEsperada;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public List<ItemLivro> getLivros() {
		return livros;
	}

	public void setLivros(List<ItemLivro> livros) {
		this.livros = livros;
	}

	@Override
	public String toString() {
		return "Emprestimo [id=" + id + ", locador=" + locador
				+ ", dataEmprestimo=" + dataEmprestimo + ", dataDevolucao="
				+ dataDevolucao + "]";
	}

}