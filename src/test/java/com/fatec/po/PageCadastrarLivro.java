package com.fatec.po;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class PageCadastrarLivro {
    private WebDriver driver;
    private By isbnBy = By.id("isbn");
    private By autorBy = By.id("autor");
    private By tituloBy = By.id("titulo");
    private By btnCadastrarBy = By.id("btnCadastrar");
    private By mensagemBy = By.id("mensagem");

    public PageCadastrarLivro(WebDriver driver){
        this.driver = driver;
    }

    /**
     * Cadastrar livro
     * @param isbn
     * @param autor
     * @param titulo
     * @return
     */
    public PageCadastrarLivro cadastrar(String isbn, String autor, String titulo) {
        driver.findElement(isbnBy).sendKeys(isbn);
        driver.findElement(autorBy).sendKeys(autor);
        driver.findElement(tituloBy).sendKeys(titulo);
        driver.findElement(btnCadastrarBy).click();

        return new PageCadastrarLivro(driver);
    }
    /*
     * Obtem a mensagem apresentada na tela ao usuario.
     */
    public String getMessageText() {
        return driver.findElement(mensagemBy).getText();
    }
}
