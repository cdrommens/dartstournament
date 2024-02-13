package be.dcharmonie.dartstournament.library.renderer.html;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

/**
 *
 */
public class HtmlRenderer {

    private static final ClassLoaderTemplateResolver TEMPLATE_RESOLVER = new ClassLoaderTemplateResolver();
    private final TemplateEngine templateEngine;

    public HtmlRenderer() {
        TEMPLATE_RESOLVER.setSuffix(".html");
        TEMPLATE_RESOLVER.setTemplateMode(TemplateMode.HTML);
        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(TEMPLATE_RESOLVER);
    }

    public String render(String template, Context context) {
        return templateEngine.process(template, context);
    }
}
