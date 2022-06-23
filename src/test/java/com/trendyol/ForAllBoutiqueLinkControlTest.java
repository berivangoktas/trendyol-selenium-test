package com.trendyol;

import com.opencsv.CSVWriter;
import com.trendyol.extensions.ReportExtension;
import com.trendyol.page.HomePage;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@ExtendWith(ReportExtension.class)
public class ForAllBoutiqueLinkControlTest extends AbstractTrendyol
{

    @Test
    public void RequestCodeControlForAllBoutiqueLinkControl() throws InterruptedException, IOException {
        HomePage loginPage = new HomePage(driver);
        String currentUrl = null;

        File file = new File("./temp/"+ returnSessionId() + ".csv");
        List<String[]> data = new ArrayList<String[]>();
        for (int i = 0; i < loginPage.boutiqueList.size(); i++) {

            click(loginPage.boutiqueList.get(i));
            currentUrl = getCurrentUrl();
            HttpURLConnection con = (HttpURLConnection) new URL(currentUrl).openConnection();

            data.add(new String[] { currentUrl,Integer.toString( con.getResponseCode() ) });
            returnBack();
        }
        createCvsFileAndWriteData(file,data);

        }

    public void createCvsFileAndWriteData(File file,List<String[]> data){
        try {
            FileWriter outputfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputfile);

            writer.writeAll(data);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
