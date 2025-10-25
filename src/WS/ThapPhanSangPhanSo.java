package WS;

public class ThapPhanSangPhanSo {
    public static int gcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static void main(String[] args) throws Exception {
//        String msv = "B22DCCN018", qCode = "ny8Fb8BU";
//        DataService_Service service = new DataService_Service();
//        DataService port = service.getDataServicePort();
//
//        double a = port.getDataDouble(msv, qCode);
//        a = Math.round(a * 100) / 100.0;
//        System.out.println("Giá trị a: " + a);
//
//        int tu = (int) Math.round(a * 100);
//        int mau = 100;
//        int tmp = gcd(tu, mau);
//        tu /= tmp;
//        mau /= tmp;
//
//        List<Integer> ans = new ArrayList<>();
//        ans.add(tu);
//        ans.add(mau);
//        System.out.println("Phân số rút gọn: " + ans);
//
//        port.submitDataIntArray(msv, qCode, ans);
//        System.out.println("Đã gửi kết quả lên server.");
    }
}
