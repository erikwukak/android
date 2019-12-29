package limfiq.inc.crudmysql;

public class ModelData {
    String nip, nama, posisi, gaji, alamat;

    public ModelData(){}

    public ModelData(String nip, String nama, String posisi, String gaji, String alamat) {
        this.nip = nip;
        this.nama = nama;
        this.posisi = posisi;
        this.gaji = gaji;
        this.alamat = alamat;
    }

    public String getNip() { return nip; }

    public void setNip(String nip) {
        this.nip = nip;
    }


    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }


    public String getPosisi() {
        return posisi;
    }

    public void setPosisi(String posisi) {
        this.posisi = posisi;
    }


    public String getGaji() {
        return gaji;
    }

    public void setGaji(String gaji) {
        this.gaji = gaji;
    }


    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
