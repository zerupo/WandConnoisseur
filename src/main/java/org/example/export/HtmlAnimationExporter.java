package org.example.export;

import org.example.main.CardHistory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Génère un fichier HTML autonome permettant de naviguer dans un CardHistory
 * directement dans le navigateur, sans appel réseau après chargement.
 *
 * Usage :
 *   Path file = HtmlAnimationExporter.export(cardHistory, "wand_123");
 *   event.getHook().sendFiles(FileUpload.fromData(file.toFile(), file.getFileName().toString())).queue();
 *   Files.deleteIfExists(file); // nettoyage optionnel après envoi
 */
public class HtmlAnimationExporter {

    /**
     * Génère le fichier HTML dans le dossier temporaire système.
     * Utile si tu veux envoyer le fichier directement en pièce jointe Discord.
     *
     * @param cardHistory l'historique à exporter
     * @param label       nom court pour le fichier (sans extension)
     * @return            chemin vers le fichier généré
     */
    public static Path export(CardHistory cardHistory, String label) throws IOException {
        String json = cardHistory.toJson();
        String html = buildHtml(json, label);
        Path   path = Files.createTempFile("noita_" + label + "_", ".html");
        Files.write(path, html.getBytes(StandardCharsets.UTF_8));
        return path;
    }

    /**
     * Génère la page HTML et l'envoie sur freekit.dev.
     * Retourne une URL publique cliquable, prête à être partagée dans Discord.
     *
     * @param cardHistory l'historique à exporter
     * @param label       nom affiché dans le titre de la page
     * @param ttl         durée de vie ("1h", "7d", "30d"…) ou null pour permanent
     * @return            URL publique (ex : https://freekit.dev/s/a1b2c3d4)
     */
    public static String exportAsUrl(CardHistory cardHistory, String label, String ttl)
            throws IOException, InterruptedException {
        String html = buildHtml(cardHistory.toJson(), label);
        return FreeKitUploader.upload(html, ttl);
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Sanitization helpers
    // ─────────────────────────────────────────────────────────────────────────

    private static String escapeHtml(String text) {
        return text
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }

    private static String escapeJsonForScriptTag(String json) {
        return json
                .replace("</", "<\\/")
                .replace("<!--", "<\\!--");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Template HTML  (un seul text block, marqueurs injectés via replace)
    // ─────────────────────────────────────────────────────────────────────────

    private static final String MARKER_TITLE = "%%TITLE%%";
    private static final String MARKER_JSON  = "%%JSON_DATA%%";

    private static String buildHtml(String json, String title) {
        String safeTitle = escapeHtml(title);
        String safeJson  = escapeJsonForScriptTag(json);

        return TEMPLATE
                .replace(MARKER_TITLE, safeTitle)
                .replace(MARKER_JSON,  safeJson);
    }

    private static final String TEMPLATE = """
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>%%TITLE%%</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link href="https://fonts.googleapis.com/css2?family=Syne:wght@400;600&family=JetBrains+Mono:wght@300;400&display=swap" rel="stylesheet">
<style>
  :root {
    --bg:          #161412;
    --surface:     #1f1c19;
    --surface-alt: #252219;
    --border:      #322e27;
    --border-soft: #2a2620;
    --text:        #d6cebe;
    --muted:       #7a7264;
    --faint:       #4a4540;
    --accent:      #c4924a;   /* ambre doux — utilisé très peu */
    --discard:     #b06858;
    --hand:        #5e8f68;
    --deck:        #5278a8;
  }

  * { box-sizing: border-box; margin: 0; padding: 0; }

  body {
    background: var(--bg);
    color: var(--text);
    font-family: 'JetBrains Mono', monospace;
    font-weight: 300;
    min-height: 100vh;
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 40px 20px 64px;
  }

  /* ── Header ── */
  header {
    width: 100%;
    max-width: 860px;
    margin-bottom: 28px;
    display: flex;
    flex-direction: column;
    gap: 7px;
  }

  header h1 {
    font-family: 'Syne', sans-serif;
    font-size: clamp(1rem, 2.5vw, 1.45rem);
    font-weight: 600;
    color: var(--text);
    letter-spacing: .04em;
  }

  #step-info {
    font-size: .68rem;
    color: var(--muted);
    letter-spacing: .07em;
  }

  /* ── Progress bar ── */
  #progress-track {
    width: 100%;
    max-width: 860px;
    height: 1px;
    background: var(--border);
    margin-bottom: 24px;
    position: relative;
  }
  #progress-fill {
    position: absolute;
    top: 0; left: 0;
    height: 100%;
    background: var(--accent);
    opacity: .6;
    transition: width .15s ease;
  }

  /* ── Main card ── */
  #card {
    width: 100%;
    max-width: 860px;
    background: var(--surface);
    border: 1px solid var(--border);
    border-radius: 5px;
    padding: 22px;
  }

  /* ── Zone label ── */
  .zone-label {
    font-size: .58rem;
    font-weight: 400;
    letter-spacing: .18em;
    text-transform: uppercase;
    color: var(--muted);
    margin-bottom: 10px;
  }

  /* ── Separator ── */
  #callstack-section {
    padding-bottom: 18px;
    margin-bottom: 18px;
    border-bottom: 1px solid var(--border-soft);
  }

  /* ── Card pool grid ── */
  #zones {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    gap: 12px;
  }

  .zone {
    padding: 13px;
    border-radius: 4px;
    background: var(--surface-alt);
    border: 1px solid var(--border-soft);
    border-left-width: 2px;
  }

  .zone.discard { border-left-color: var(--discard); }
  .zone.hand    { border-left-color: var(--hand);    }
  .zone.deck    { border-left-color: var(--deck);    }

  .zone.discard .zone-label { color: var(--discard); }
  .zone.hand    .zone-label { color: var(--hand);    }
  .zone.deck    .zone-label { color: var(--deck);    }

  /* ── Spell strip ── */
  .spell-strip {
    display: flex;
    flex-wrap: wrap;
    gap: 4px;
    min-height: 34px;
    align-items: center;
  }

  .spell-icon {
    width: 30px;
    height: 30px;
    image-rendering: pixelated;
    border-radius: 2px;
    background: var(--border-soft);
    flex-shrink: 0;
    opacity: .88;
    transition: transform .1s, opacity .1s;
  }
  .spell-icon:hover {
    transform: scale(1.7);
    opacity: 1;
    z-index: 10;
    position: relative;
  }

  .spell-text {
    font-size: .6rem;
    color: var(--muted);
    padding: 2px 5px;
    background: var(--border-soft);
    border-radius: 2px;
  }

  .overflow-badge {
    font-size: .6rem;
    color: var(--faint);
    padding: 2px 6px;
    border: 1px solid var(--border);
    border-radius: 2px;
    flex-shrink: 0;
  }

  .empty {
    color: var(--faint);
    font-size: .65rem;
  }

  /* ── Controls ── */
  #controls {
    display: flex;
    gap: 5px;
    justify-content: center;
    margin-top: 22px;
    flex-wrap: wrap;
    align-items: center;
  }

  /*
   * Les boutons de navigation utilisent system-ui pour garantir
   * un rendu correct des glyphes fléchés (← → « »),
   * indépendamment de la police monospace principale.
   */
  button {
    background: var(--surface);
    color: var(--text);
    border: 1px solid var(--border);
    border-radius: 4px;
    padding: 7px 15px;
    font-family: system-ui, sans-serif;
    font-size: .82rem;
    line-height: 1;
    cursor: pointer;
    transition: color .12s, border-color .12s, background .12s;
    display: inline-flex;
    align-items: center;
    justify-content: center;
    min-width: 36px;
  }

  button:hover {
    background: var(--surface-alt);
    border-color: var(--accent);
    color: var(--accent);
  }

  button:active { transform: translateY(1px); }

  /* ── GoTo ── */
  #goto-row {
    display: flex;
    gap: 4px;
    align-items: center;
  }

  #goto-input {
    background: var(--bg);
    border: 1px solid var(--border);
    border-radius: 4px;
    color: var(--text);
    font-family: 'JetBrains Mono', monospace;
    font-weight: 300;
    font-size: .72rem;
    padding: 6px 10px;
    width: 80px;
    text-align: center;
    outline: none;
    transition: border-color .12s;
  }

  #goto-input:focus { border-color: var(--accent); }

  /* GoTo label — pas une flèche, du texte lisible */
  #goto-row button {
    font-family: 'JetBrains Mono', monospace;
    font-size: .7rem;
    padding: 7px 12px;
    letter-spacing: .05em;
  }

  /* ── Keyboard hint ── */
  #hint {
    margin-top: 14px;
    font-size: .58rem;
    color: var(--faint);
    text-align: center;
    letter-spacing: .07em;
  }

  kbd {
    background: var(--surface);
    border: 1px solid var(--border);
    border-radius: 2px;
    padding: 1px 5px;
    font-family: system-ui, sans-serif;
    font-size: .58rem;
    color: var(--muted);
  }

  @media (max-width: 600px) {
    #zones { grid-template-columns: 1fr; }
  }
</style>
</head>
<body>

<header>
  <h1>%%TITLE%%</h1>
  <div id="step-info">—</div>
</header>

<div id="progress-track"><div id="progress-fill"></div></div>

<div id="card">
  <div id="callstack-section">
    <div class="zone-label">Call Stack</div>
    <div class="spell-strip" id="zone-callstack"></div>
  </div>
  <div id="zones">
    <div class="zone discard">
      <div class="zone-label">Discard</div>
      <div class="spell-strip" id="zone-discard"></div>
    </div>
    <div class="zone hand">
      <div class="zone-label">Hand</div>
      <div class="spell-strip" id="zone-hand"></div>
    </div>
    <div class="zone deck">
      <div class="zone-label">Deck</div>
      <div class="spell-strip" id="zone-deck"></div>
    </div>
  </div>
</div>

<div id="controls">
  <button onclick="navigate(-Infinity)" title="Premier step">&#171;</button>
  <button onclick="navigate(-10)"       title="-10">&#8722;10</button>
  <button onclick="navigate(-1)"        title="Précédent">&#8592;</button>
  <div id="goto-row">
    <input id="goto-input" type="text" placeholder="step…">
    <button onclick="gotoStep()">go</button>
  </div>
  <button onclick="navigate(+1)"        title="Suivant">&#8594;</button>
  <button onclick="navigate(+10)"       title="+10">+10</button>
  <button onclick="navigate(+Infinity)" title="Dernier step">&#187;</button>
</div>

<div id="hint">
  <kbd>&larr;</kbd> <kbd>&rarr;</kbd> naviguer &nbsp;&middot;&nbsp;
  <kbd>Shift</kbd> + <kbd>&larr;&rarr;</kbd> &plusmn;10 &nbsp;&middot;&nbsp;
  <kbd>Ctrl</kbd> + <kbd>&larr;&rarr;</kbd> début / fin
</div>

<script>
const DATA = %%JSON_DATA%%;

let current = 0;
const total  = DATA.frames.length;

function emoteUrl(emote) {
  const m = emote.match(/<:(\\w+):(\\d+)>/);
  if (m) return `https://cdn.discordapp.com/emojis/${m[2]}.webp?size=32&quality=lossless`;
  return null;
}

function renderStrip(containerId, spellIds, maxWidth = 800) {
  const el     = document.getElementById(containerId);
  el.innerHTML = '';
  let usedPx   = 0;
  const ICON   = 34;

  for (let i = 0; i < spellIds.length; i++) {
    const spell = DATA.spells[spellIds[i]];
    const url   = emoteUrl(spell.emote);

    if (usedPx + ICON > maxWidth) {
      const badge = document.createElement('span');
      badge.className   = 'overflow-badge';
      badge.textContent = '+' + (spellIds.length - i);
      el.appendChild(badge);
      break;
    }

    if (url) {
      const img = document.createElement('img');
      img.className = 'spell-icon';
      img.src   = url;
      img.alt   = spell.name;
      img.title = spell.name;
      el.appendChild(img);
    } else {
      const span = document.createElement('span');
      span.className   = 'spell-text';
      span.textContent = spell.name;
      el.appendChild(span);
    }
    usedPx += ICON;
  }

  if (spellIds.length === 0) {
    const empty = document.createElement('span');
    empty.className   = 'empty';
    empty.textContent = '—';
    el.appendChild(empty);
  }
}

function render() {
  const frame = DATA.frames[current];
  const meta  = frame.meta;

  document.getElementById('step-info').textContent =
    `step ${current + 1} / ${total}` +
    `  ·  card ${meta.cardPoolStep + 1}/${meta.cardPoolSize}` +
    `  ·  cast ${meta.callStackStep + 1}/${meta.callStackSize}`;

  document.getElementById('progress-fill').style.width =
    ((current / Math.max(total - 1, 1)) * 100) + '%';

  renderStrip('zone-callstack', frame.callStack);
  renderStrip('zone-discard',   frame.discard);
  renderStrip('zone-hand',      frame.hand);
  renderStrip('zone-deck',      frame.deck);
}

function navigate(delta) {
  if      (delta === -Infinity) current = 0;
  else if (delta === +Infinity) current = total - 1;
  else current = ((current + delta) % total + total) % total;
  render();
}

function gotoStep() {
  const n = parseInt(document.getElementById('goto-input').value.trim(), 10);
  if (!isNaN(n) && n >= 1 && n <= total) {
    current = n - 1;
    render();
  }
  document.getElementById('goto-input').value = '';
}

document.getElementById('goto-input').addEventListener('keydown', e => {
  if (e.key === 'Enter') gotoStep();
  e.stopPropagation();
});

document.addEventListener('keydown', e => {
  if (document.activeElement === document.getElementById('goto-input')) return;
  if (e.key === 'ArrowRight') navigate(e.shiftKey ? 10 : e.ctrlKey ? +Infinity : 1);
  if (e.key === 'ArrowLeft')  navigate(e.shiftKey ? -10 : e.ctrlKey ? -Infinity : -1);
});

render();
</script>
</body>
</html>
""";
}
