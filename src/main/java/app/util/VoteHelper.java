package app.util;

import com.sun.istack.internal.Nullable;
import jxl.Workbook;
import jxl.write.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

public class VoteHelper {

    private static final String FILENAME = "results.txt";

    public static final String[][] voteOptions = new String[][]{
            {"asp","ASP.NET"},
            {"js","JavaScript"},
            {"php","PHP"},
            {"py","Python"},
            {"rr","RubyOnRails"},
            {"jsp","Servlets & JSP"}
    };

    private static final Map<String, String> voteOptionsMap;

    static {
        voteOptionsMap = new HashMap<>();
        for (String[] keyValue: voteOptions) voteOptionsMap.put(keyValue[0], keyValue[1]);
    }

    public List<String> getSelectedTechnologies(Collection<String> systemNames, @Nullable Set<String> filteredSystemNames){
        List<String> list = new ArrayList<>(systemNames.size());
        for(String systemName: systemNames){
            String value = getFullName(systemName);
            if (value!=null) {
                list.add(value);
                if (filteredSystemNames!=null) filteredSystemNames.add(systemName);
            }
        }
        return list;
    }

    public String getFullName(String shortName){
        return voteOptionsMap.get(shortName);
    }

    public void writeResults(HashMap<String, Integer> results){
        try {
            FileWriter fileWriter = new FileWriter(FILENAME);
            for (String key : results.keySet()) {
                fileWriter.write(key + ":" + results.get(key) + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, Integer> readResults(){
        HashMap<String, Integer> results = new HashMap<>();
        File file = new File(FILENAME);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String[] elem = line.split(":");
                results.put(elem[0], Integer.parseInt(elem[1]));
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }

    public void addAndWriteResults(Set<String> values){
        HashMap<String, Integer> map = readResults();
        for (String value: values){
            Integer count = map.get(value);
            if (count==null) count = 0;
            count++;
            map.put(value, count);
        }
        writeResults(map);
    }

    public void writeExcel(OutputStream out) throws IOException, WriteException {
        WritableWorkbook w = Workbook.createWorkbook(out);
        WritableSheet s = w.createSheet("Demo", 0);
        s.addCell(new Label(0, 0, "Technologia"));
        s.addCell(new Label(1, 0, "Głosy"));
        HashMap<String, Integer> results = readResults();
        int row = 1;
        for (Map.Entry<String, Integer> entry: results.entrySet()){
            s.addCell(new Label(0, row, getFullName(entry.getKey())));
            s.addCell(new Label(1, row, entry.getValue().toString()));
            row++;
        }
        w.write();
        w.close();
    }

    public void writeChart(OutputStream out) throws IOException{
        DefaultPieDataset dataset = new DefaultPieDataset();
        HashMap<String, Integer> results = readResults();
        for (Map.Entry<String, Integer> entry: results.entrySet())
            dataset.setValue(getFullName(entry.getKey()), entry.getValue());
        JFreeChart chart = ChartFactory.createPieChart(
                "Pie Chart",
                dataset,
                true,
                true,
                false
        );

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionOutlinesVisible(false);
        plot.setNoDataMessage("No data available");
        BufferedImage img = chart.createBufferedImage(1000, 1000, BufferedImage.TYPE_3BYTE_BGR, null);
        javax.imageio.ImageIO.write(img, "png", out);
        out.close();
    }
}
