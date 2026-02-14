package direction.systems.direction.entities;

import jakarta.persistence.*;

@Entity
public class Produto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;

  private Long preco;

  private String quantidade;

  private String categoria;

  @ManyToOne
  @JoinColumn(name = "usuario_id")
  private Usuario usuario;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Long getPreco() {
    return preco;
  }

  public void setPreco(Long preco) {
    this.preco = preco;
  }

  public String getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(String quantidade) {
    this.quantidade = quantidade;
  }

  public String getCategoria() {
    return categoria;
  }

  public void setCategoria(String categoria) {
    this.categoria = categoria;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }
}
