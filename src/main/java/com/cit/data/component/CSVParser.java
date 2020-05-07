package com.cit.data.component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class CSVParser {

    public String parser(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String[] header = bufferedReader.readLine().split(",");
        Map<String, Map<String, String>> paramMap = new HashMap<String, Map<String, String>>();
        bufferedReader.lines().forEach(line -> {
            String[] params = line.split(",");
            Map<String, String> map = new HashMap<String, String>();
            for(int i = 1; i < params.length; i++)
                map.put(header[i], params[i]);
            paramMap.put(params[0], map);
        });

        return null;
    }
}
