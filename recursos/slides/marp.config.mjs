// Configuração do Marp para as apresentações da trilha.
// Usada pelo recursos/slides/gerar.sh via --config.

export default {
  // A capa usa <div>, então as tags HTML precisam estar liberadas.
  html: true,

  options: {
    /*
     * Desliga o Twemoji.
     *
     * Por padrão o Marp substitui CADA emoji do texto por um
     *   <img class="emoji" src="https://cdn.jsdelivr.net/gh/jdecked/twemoji@…">
     * o que traz três problemas para um material de aula:
     *
     *   1. exige internet para GERAR o PDF;
     *   2. exige internet para EXIBIR o HTML — justamente numa sala de aula,
     *      onde o wifi é a primeira coisa a falhar;
     *   3. transforma emoji em imagem, e qualquer regra CSS de `img` passa a
     *      valer para ele (foi assim que um 💡 de callout virou bloco
     *      centralizado numa linha só).
     *
     * Com `false`, o emoji continua sendo texto e é desenhado pela fonte do
     * sistema (Apple Color Emoji no macOS), ficando embutido no PDF.
     */
    emoji: { shortcode: false, unicode: false },
  },
};
