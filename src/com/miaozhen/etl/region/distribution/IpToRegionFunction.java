package com.miaozhen.etl.region.distribution;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

/**
 * Created by Season on 14-7-28.
 */
public class IpToRegionFunction {

    //public static final Log l4j = LogFactory.getLog(IpToRegionFunction.class);

    private TreeMap<Pair, Integer> iplib = null;
    //public static int count = 0;

    public Integer evaluate(String ip, String iplibFile) throws IOException {
        long sum = 0L;

        if (iplib == null) {
            iplib = new TreeMap<Pair, Integer>();
            //l4j.info(String.format("----initialize ip lib %s times!!!!-----", ++count));
            readFile(iplibFile);
        }
        String[] parts = ip.split("\\.");
        if (parts.length == 4) {
            long a = Long.valueOf(parts[0]);
            long b = Long.valueOf(parts[1]);
            long c = Long.valueOf(parts[2]);
            long d = Long.valueOf(parts[3]);

            sum = a * 256l * 256l * 256l + b * 256l * 256l + c * 256l + d;
        }
        Pair pair = new Pair( sum , sum );
       /* for (Pair pair : iplib.keySet()) {

            if (pair.isBetween(sum)) {
                return iplib.get(pair);
            }
        }*/
        return iplib.get(iplib.floorKey(pair)) ;
        // return null;
    }

    private void readFile(String filename) throws IOException {
        Configuration conf = new Configuration();
        Path path = new Path(filename);

        String line;
        BufferedReader in = null;
        try {
            FileSystem fs = path.getFileSystem(conf);
            in = new BufferedReader(new InputStreamReader(fs.open(path)));
            int columns = 0;
            while ((line = in.readLine()) != null) {
                String[] arr = line.split(",");
                if (columns == 0) {
                    columns = arr.length;
                }
                if (columns == 4 && arr[0].equals("0")) {
                    iplib.put(new Pair(Long.valueOf(arr[1]), Long.valueOf(arr[2])), Integer.parseInt(arr[3]));
                } else if (columns == 3 && arr.length == 3) {
                    iplib.put(new Pair(Long.valueOf(arr[0]), Long.valueOf(arr[1])), Integer.parseInt(arr[2]));
                }

            }
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    private static class Pair implements Comparable{
        final long start;
        final long end;

        Pair(long start, long end) {
            this.start = start;
            this.end = end;
        }

        private boolean isBetween(long ip) {
            if (ip >= start && ip <= end)
                return true;
            return false;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (end != pair.end) return false;
            if (start != pair.start) return false;

            return true;
        }
        public int compareTo(Object other) {
            long other_start = ((Pair)other).start;
            if( start != other_start )
                return (start < other_start) ? -1 : 1;
            else
                return 0;
          /*  else {
                long other_end = ((Pair)other).end;
                return (end < other_end) ? -1 : 1;
            }*/

        }
        @Override
        public int hashCode() {
            int result = (int) (start ^ (start >>> 32));
            result = 31 * result + (int) (end ^ (end >>> 32));
            return result;
        }
    }
}
