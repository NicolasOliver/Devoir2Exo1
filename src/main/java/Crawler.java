import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


public class Crawler {
    private static String URL = "http://paizo.com/pathfinderRPG/prd/indices/bestiary.html";
    private static ArrayList<Monster> listMonsters = new ArrayList<Monster>();

    public static void main(String[] args) throws IOException {
        findMonsters(URL);
    }

    public static void findMonsters(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements content = doc.getElementsByClass("index");
        Elements sectionMonsters = content.select("ul");

        for(Element monsters : sectionMonsters) {
            Elements monstersList = monsters.select("li");

            for(Element monster : monstersList) {
                System.out.println(monster);
                String url_monster = monster.child(0).attr("href").replace("../","/");
                System.out.println(url_monster);
                listMonsters = findMonster(url_monster);
            }
        }
    }

    private static ArrayList<Monster> findMonster(String url_monster) {
        return listMonsters;
    }
}
