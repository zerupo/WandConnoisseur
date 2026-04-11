package org.example.export;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * Envoie une page HTML sur freekit.dev et retourne l'URL publique.
 *
 * Aucune dépendance externe — utilise uniquement le HttpClient du JDK 11+.
 * Aucun compte, aucune clé API, aucun serveur à gérer.
 *
 * Exemple d'usage dans le bot :
 *
 *   String url = FreeKitUploader.upload(html, "1d");
 *   event.getHook().sendMessage("Ton wand → " + url).queue();
 */
public class FreeKitUploader {

    private static final String API_URL  = "https://freekit.dev/api/v1/sites";
    private static final Duration TIMEOUT = Duration.ofSeconds(15);

    private static final HttpClient HTTP = HttpClient.newBuilder()
            .connectTimeout(TIMEOUT)
            .build();

    /**
     * Envoie le HTML sur freekit.dev.
     *
     * @param html contenu HTML complet
     * @param ttl  durée de vie (ex : "1h", "7d", "30d") — null pour permanent
     * @return URL publique de la page (ex : https://freekit.dev/s/a1b2c3d4)
     * @throws IOException          en cas d'erreur réseau
     * @throws InterruptedException si le thread est interrompu
     * @throws UploadException      si l'API retourne une erreur
     */
    public static String upload(String html, String ttl)
            throws IOException, InterruptedException {

        String body = buildJson(html, ttl);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .timeout(TIMEOUT)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = HTTP.send(
                request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 201) {
            throw new UploadException(
                    "FreeKit a répondu " + response.statusCode()
                    + " : " + response.body());
        }

        return parseUrl(response.body());
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    /**
     * Construit le JSON manuellement — évite toute dépendance sur Gson/Jackson.
     * Les seuls caractères à échapper dans le HTML sont " et \ ,
     * ce qui est déjà géré par {@code escapeJsonForScriptTag} dans
     * {@link HtmlAnimationExporter} en amont.
     */
    private static String buildJson(String html, String ttl) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"html\":\"");
        sb.append(escapeJsonString(html));
        sb.append("\"");
        if (ttl != null && !ttl.isBlank()) {
            sb.append(",\"ttl\":\"").append(ttl).append("\"");
        }
        sb.append("}");
        return sb.toString();
    }

    /**
     * Échappe les caractères JSON obligatoires dans une chaîne
     * (guillemets, backslash, et caractères de contrôle).
     */
    private static String escapeJsonString(String s) {
        StringBuilder sb = new StringBuilder(s.length() + 64);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '"'  -> sb.append("\\\"");
                case '\\' -> sb.append("\\\\");
                case '\n' -> sb.append("\\n");
                case '\r' -> sb.append("\\r");
                case '\t' -> sb.append("\\t");
                default   -> {
                    if (c < 0x20) {
                        sb.append(String.format("\\u%04x", (int) c));
                    } else {
                        sb.append(c);
                    }
                }
            }
        }
        return sb.toString();
    }

    /**
     * Extrait le champ {@code url} du JSON de réponse sans dépendance externe.
     *
     * Réponse attendue :
     * {"status":"success","data":{"siteId":"…","url":"https://freekit.dev/s/…",…}}
     */
    private static String parseUrl(String json) {
        String marker = "\"url\":\"";
        int start = json.indexOf(marker);
        if (start == -1) {
            throw new UploadException("Champ 'url' absent de la réponse : " + json);
        }
        start += marker.length();
        int end = json.indexOf('"', start);
        if (end == -1) {
            throw new UploadException("Réponse JSON malformée : " + json);
        }
        return json.substring(start, end);
    }

    // ── Exception ────────────────────────────────────────────────────────────

    /** Erreur levée quand l'API FreeKit retourne une réponse inattendue. */
    public static class UploadException extends RuntimeException {
        public UploadException(String message) {
            super(message);
        }
    }
}
