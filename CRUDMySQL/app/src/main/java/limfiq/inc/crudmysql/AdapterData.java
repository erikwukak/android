package limfiq.inc.crudmysql;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData> {
    private List<ModelData> mItems ;
    private Context context;

    public AdapterData (Context context, List<ModelData> items)
    {
        this.mItems = items;
        this.context = context;
    }

    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row,parent,false);
        HolderData holderData = new HolderData(layout);
        return holderData;
    }

    @Override
    public void onBindViewHolder(HolderData holder, int position) {
        ModelData md  = mItems.get(position);
        holder.tvnama.setText(md.getNama());
        holder.tvnip.setText(md.getNip());
        holder.md = md;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    class HolderData extends RecyclerView.ViewHolder
    {
        TextView tvnama,tvnip, tvposisi, tvgaji, tvalamat;
        ModelData md;

        public  HolderData (View view)
        {
            super(view);

            tvnip = (TextView) view.findViewById(R.id.nip);
            tvnama = (TextView) view.findViewById(R.id.nama);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent update = new Intent(context, InsertData.class);
                    update.putExtra("update",1);
                    update.putExtra("nip",md.getNip());
                    update.putExtra("nama",md.getNama());
                    update.putExtra("posisi",md.getPosisi());
                    update.putExtra("gaji",md.getGaji());
                    update.putExtra("alamat",md.getAlamat());

                    context.startActivity(update);
                }
            });
        }
    }
}
