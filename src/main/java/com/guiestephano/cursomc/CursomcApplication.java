package com.guiestephano.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.guiestephano.cursomc.domain.Categoria;
import com.guiestephano.cursomc.domain.Cidade;
import com.guiestephano.cursomc.domain.Cliente;
import com.guiestephano.cursomc.domain.Endereco;
import com.guiestephano.cursomc.domain.Estado;
import com.guiestephano.cursomc.domain.Produto;
import com.guiestephano.cursomc.domain.enums.TipoCliente;
import com.guiestephano.cursomc.repositories.CategoriaRepository;
import com.guiestephano.cursomc.repositories.CidadeRepository;
import com.guiestephano.cursomc.repositories.ClienteRepository;
import com.guiestephano.cursomc.repositories.EnderecoRepository;
import com.guiestephano.cursomc.repositories.EstadoRepository;
import com.guiestephano.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritório");
		
		Produto p1 = new Produto(null,"Computador", 2000.00);
		Produto p2 = new Produto(null,"Impressora", 800.000);
		Produto p3 = new Produto(null,"Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
				
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade cid1 = new Cidade(null, "Uberlândia", est1);
		Cidade cid2  = new Cidade(null,"São Paulo", est2);
		Cidade cid3 = new Cidade(null,"Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2,cid3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(cid1,cid2,cid3));

		Cliente cli1 = new Cliente(null,"Maria Silva", "maria@gmail.com", "16548926578", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("65814235", "87451246"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300","apto 230", "Jardim", "38220384", cli1, cid1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "sala800", "Centro", "38777012", cli1, cid2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
	}

}
