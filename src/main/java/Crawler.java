import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


public class Crawler {
    private static String URL = "http://legacy.aonprd.com/bestiary2/additionalMonsterIndex.html";
    private static ArrayList<Monster> listMonsters = new ArrayList<Monster>();

    public static void main(String[] args) throws IOException {
        findMonsters(URL);
        //findMonster("http://legacy.aonprd.com/bestiary2/agathion.html#agathion");
        //findMonster("http://legacy.aonprd.com/bestiary2/daemon.html#daemon");
        //findMonster("http://legacy.aonprd.com/bestiary2/demon.html");

    }

    public static void findMonsters(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements content = doc.getElementsByClass("index");
        Elements sectionMonsters = content.select("ul");

        for(Element monsters : sectionMonsters) {
            Elements monstersList = monsters.select("li");

            for(Element monster : monstersList) {
                String url_monster = monster.child(0).attr("href").replace("../","/");
                System.out.println(url_monster);
                findMonster(url_monster);
            }
        }
    }

    private static void findMonster(String url_monster) throws IOException {
        Document doc = Jsoup.connect("http://legacy.aonprd.com/bestiary2/"+url_monster).get();
        Elements content = doc.getElementsByClass("body");

        Elements elements = content.select("div");
        // Supprime des divs inutiles
        elements.remove(0);
        elements.remove(elements.size()-1);
        //
        for (Element e : elements) {
            String[] name = e.getElementsByClass("stat-block-title").text().split(" ");
            if(ifExist(name[0])) {
                System.out.println("Monstre déjà enregistré");
            }
            else {
                if(name[0].length()!=0) {
                    Monster m = new Monster();
                    ArrayList<String> spellsList = new ArrayList<String>();
                    m.setName(name[0]);
                    Elements spells = e.getElementsByAttributeValueContaining("href","/spells/");
                    for(Element spell : spells) {
                        spellsList.add(spell.text());
                        m.setSpells(spellsList);
                    }
                    System.out.println(m.toString());
                    listMonsters.add(m);
                }
            }
            System.out.println("=======================");
        }
    }

    private static Boolean ifExist(String name) {
        Boolean bool = false;
        for(Monster m : listMonsters) {
            if(m.getName().equals(name)) bool = true;
        }
        return bool;
    }
}
