import org.junit.Test;

import java.util.Random;

public class InsertRandomSQLValues {

    @Test
    public void print(){
        int count = 40;
        StringBuilder sb = new StringBuilder("INSERT INTO `customer` (`name`, `phone`, `email`, `city`) VALUES \n");
        Random random = new Random();
        for (int i = 0; i < count; i++){
            String name = names[random.nextInt(names.length)];
            String city = cities[random.nextInt(cities.length)];
            sb.append("('"+name+"', '"+randomPhone(random)+"', '"+randomEmail(name, random)+"', '"+city+"')");
            if (i < count-1)
                sb.append(",\n");
            else
                sb.append(";");
        }
        System.out.println("===============================");
        System.out.println("QUERY: ");
        System.out.println(sb.toString());
        System.out.println("===============================");
    }

    private String randomPhone(Random random){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 12; i++){
            if (i==3 || i == 7){
                stringBuilder.append("-");
            } else {
                stringBuilder.append(random.nextInt(10));
            }
        }
        return stringBuilder.toString();
    }

    private String randomEmail(String name, Random random){
        return name.replaceAll("\\s+","").toLowerCase()+random.nextInt(100)+"e@gmail.com";
    }

    private final static String[] names = {
            "Jacob Dostal",
            "Emogene Arana",
            "Jannie Mowrer",
            "Marcie Lagrone",
            "Pok Brian",
            "Coretta Grote",
            "Wilhelmina Ramon",
            "Lauran Rumery",
            "Nicolas Murguia",
            "Ilda Polston",
            "Malvina Kirkpatrick",
            "Carmelina Stamper",
            "Carolann Ramsburg",
            "Philip Rinker",
            "Emmy Fielder",
            "Alissa Yopp",
            "Jazmine Goley",
            "Nelida Troncoso",
            "Astrid Verret",
            "Raye Haecker",
            "Fredericka Montesano",
            "Alfonzo Chee",
            "Ocie Vankleeck",
            "Elva Lucht",
            "Sandie Coombs",
            "Vashti Passarelli",
            "Edmundo Tatom",
            "Ceola Eveland",
            "Hedwig Ensor",
            "Joel Truluck",
            "Corinne Aubert",
            "Delfina Fritsche",
            "Roxy Murrell",
            "Araceli Carrico",
            "Diane Greening",
            "Robena Swartwood",
            "Veronika Mcelveen",
            "Roseline Leonard",
            "Elton Antle"
    };

    private final static String[] cities = {
            "New York",
            "Los Angeles",
            "Chicago",
            "Houston",
            "Philadelphia",
            "Phoenix",
            "San Antonio",
            "San Diego",
            "Dallas",
            "San Jose",
            "Austin",
            "Indianapolis",
            "Jacksonville",
            "San Francisco",
            "Columbus",
            "Charlotte",
            "Fort Worth",
            "Detroit",
            "El Paso",
            "Memphis",
            "Seattle",
            "Denver",
            "Washington",
            "Boston",
            "Nashville",
            "Baltimore",
            "Oklahoma City",
            "Louisville",
            "Portland",
            "Las Vegas",
            "Milwaukee",
            "Albuquerque",
            "Tucson",
            "Fresno",
            "Sacramento",
            "Long Beach",
            "Kansas City",
            "Mesa",
            "Virginia Beach",
            "Atlanta",
            "Colorado Springs",
            "Omaha",
            "Raleigh",
            "Miami",
            "Oakland",
            "Minneapolis",
            "Tulsa",
            "Cleveland",
            "Wichita",
            "Arlington",
            "New Orleans",
            "Bakersfield",
            "Tampa",
            "Honolulu",
            "Aurora",
            "Anaheim",
            "Santa Ana",
            "St Louis",
            "Riverside",
            "Corpus Christi",
            "Lexington",
            "Pittsburgh",
            "Anchorage",
            "Stockton",
            "Cincinnati",
            "Saint Paul",
            "Toledo",
            "Greensboro",
            "Newark",
            "Plano",
            "Henderson",
            "Lincoln",
            "Buffalo",
            "Jersey City",
            "Chula Vista",
            "Fort Wayne",
            "Orlando",
            "St Petersburg",
            "Chandler",
            "Laredo",
            "Norfolk",
            "Durham",
            "Madison",
            "Lubbock",
            "Irvine",
            "Winston–Salem",
            "Glendale",
            "Garland",
            "Hialeah",
            "Reno"
    };
}
