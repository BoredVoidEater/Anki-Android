package com.ichi2.libanki;

import android.content.Context;

import com.ichi2.anki.RobolectricTest;
import com.ichi2.libanki.Card;
import com.ichi2.libanki.Collection;
import com.ichi2.libanki.Models;
import com.ichi2.libanki.Note;
import com.ichi2.libanki.template.Template;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

@RunWith(AndroidJUnit4.class)
public class MathJaxClozeTest extends RobolectricTest {

    @Test
    public void removeFormattingFromMathjax() {
        final String original_s = "{{c1::ok}} \\(2^2\\) {{c2::not ok}} \\(2^{{c3::2}}\\) \\(x^3\\) {{c4::blah}} {{c5::text with \\(x^2\\) jax}}";

        assertEquals(original_s, Template.removeFormattingFromMathjax(original_s, "1"));
        assertEquals(original_s, Template.removeFormattingFromMathjax(original_s, "2"));
        assertEquals(original_s, Template.removeFormattingFromMathjax(original_s, "4"));
        assertEquals(original_s, Template.removeFormattingFromMathjax(original_s, "5"));

        final String escaped_s = "{{c1::ok}} \\(2^2\\) {{C2::not ok}} \\(2^{{C3::2}}\\) \\(x^3\\) {{c4::blah}} {{c5::text with \\(x^2\\) jax}}";
        assertEquals(escaped_s, Template.removeFormattingFromMathjax(original_s, "3"));

    }

    @Test
    public void verifyMathJaxClozeCards() {
        final Context context = ApplicationProvider.getApplicationContext();

        Collection c = getCol();
        Note f = c.newNote(c.getModels().byName("Cloze"));
        f.setItem("Text", "{{c1::ok}} \\(2^2\\) {{c2::not ok}} \\(2^{{c3::2}}\\) \\(x^3\\) {{c4::blah}} {{c5::text with \\(x^2\\) jax}}");
        c.addNote(f);
        assertEquals(5, f.cards().size());

        ArrayList<Card> cards = f.cards();

        assertTrue(cards.get(0).q().contains("class=cloze"));
        assertTrue(cards.get(1).q().contains("class=cloze"));
        String s = cards.get(2).q();
        assertFalse(cards.get(2).q().contains("class=cloze"));
        assertTrue(cards.get(3).q().contains("class=cloze"));
        assertTrue(cards.get(4).q().contains("class=cloze"));
    }
}
