package org.example.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.utils.FileUpload;
import org.example.main.Global;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.AlphaComposite;

public class TextCommand implements Command{
    @Override
    public String getName(){
        return "texte";
    }

    @Override
    public String getDescription(){
        return "Renvoie une image du texte sur fond transparent écrit avec une font de noita.";
    }

    private static BufferedImage scaleGradient(BufferedImage originalImage, int newHeight){
        if (originalImage == null) {
            return null;
        }
        int newWidth = (int)Math.ceil((double)originalImage.getWidth()*(double)newHeight/(double)originalImage.getHeight());

        BufferedImage scaledImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = scaledImage.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

        g2d.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
        g2d.dispose();

        return scaledImage;
    }

    @Override
    public void executeSlash(SlashCommandInteractionEvent event){
        OptionMapping texteOption = event.getOption("texte");
        OptionMapping fontOption = event.getOption("font");
        OptionMapping tailleOption = event.getOption("taille");
        OptionMapping couleurOption = event.getOption("couleur");
        String texte = "";
        String fontTexte = "title";
        int taille = 100;
        Color couleur = new Color(255, 255, 255, 255);
        BufferedImage gradientImage;
        Graphics2D g2d;
        Font font;

        if(texteOption != null){
            texte = texteOption.getAsString();
        }
        if(fontOption != null){
            fontTexte = fontOption.getAsString();
        }
        if(tailleOption != null){
            taille = tailleOption.getAsInt();
        }
        if(couleurOption != null){
            Pattern p = Pattern.compile("^([0-9a-f]{2})([0-9a-f]{2})([0-9a-f]{2})([0-9a-f]{2})?$");
            Matcher m = p.matcher(couleurOption.getAsString().toLowerCase());
            if(m.find()){
                System.out.println(couleurOption.getAsString().toLowerCase() + " is matching, alpha = " + (m.group(4) != null));
                if(m.group(4) != null){
                    System.out.println("RGBA");
                    couleur = new Color(Integer.parseInt(m.group(1), 16), Integer.parseInt(m.group(2), 16), Integer.parseInt(m.group(3), 16), Integer.parseInt(m.group(4), 16));
                }else{
                    System.out.println("RGB");
                    couleur = new Color(Integer.parseInt(m.group(1), 16), Integer.parseInt(m.group(2), 16), Integer.parseInt(m.group(3), 16), 255);
                }
            }
        }

        try{
            InputStream myStream = null;
            switch (fontTexte) {
                case "pixel" -> myStream = new FileInputStream("./src/main/java/org/example/PixelFont.ttf");
                case "title" -> myStream = new FileInputStream("./src/main/java/org/example/TitleFont.ttf");
                case "glyph" -> {
                    myStream = new FileInputStream("./src/main/java/org/example/GlyphFont.ttf");
                    texte = texte.replace(" ", "   ");
                }
                default -> {}
            }
            if(myStream != null){
                Font baseFont = Font.createFont(Font.TRUETYPE_FONT, myStream);
                font = baseFont.deriveFont(Font.PLAIN, taille);
            }else{
                font = new Font("Arial", Font.PLAIN, taille);
            }

            BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
            Graphics2D tempG2d = image.createGraphics();
            tempG2d.setFont(font);
            FontMetrics fm = tempG2d.getFontMetrics();
            int width = fm.stringWidth(texte);
            int height = fm.getHeight() + fm.getMaxDescent()*2;
            tempG2d.dispose();

            image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            if(fontTexte.equals("title") && couleurOption == null){
                gradientImage = scaleGradient(Global.loadImage("./src/main/java/org/example/image/other/gradient.png"), height);

                BufferedImage mask = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                Graphics2D maskG2d = mask.createGraphics();
                maskG2d.setFont(font);
                maskG2d.setColor(Color.BLACK);
                maskG2d.drawString(texte, 0, fm.getAscent());
                maskG2d.dispose();

                Graphics2D resultG2d = image.createGraphics();
                Rectangle gradientRect = new Rectangle(0, 0, gradientImage.getWidth(), gradientImage.getHeight());
                TexturePaint texture = new TexturePaint(gradientImage, gradientRect);
                resultG2d.setPaint(texture);
                resultG2d.fillRect(0, 0, width, height);
                resultG2d.setComposite(AlphaComposite.DstIn);
                resultG2d.drawImage(mask, 0, 0, null);
                resultG2d.dispose();
            }else{
                g2d = image.createGraphics();
                g2d.setFont(font);
                g2d.setColor(couleur);
                g2d.drawString(texte, 0, fm.getHeight());
                g2d.dispose();
            }

            File textFile = new File("./src/main/java/org/example/fileOutput/text_" + event.getId() + ".png");
            ImageIO.write(image, "PNG", textFile);
            event.reply("OK").setEphemeral(true).queue(message -> {
                message.deleteOriginal().queue();
            });
            event.getChannel().sendFiles(FileUpload.fromData(textFile, "text.png")).queue();
            if(!textFile.delete()){
                System.out.println("\"./src/main/java/org/example/fileOutput/text_" + event.getId() + ".png\" not deleted");
            }
        }catch(Exception e){
            event.reply("Erreur lors de la création de l'image").setEphemeral(true).queue();
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}