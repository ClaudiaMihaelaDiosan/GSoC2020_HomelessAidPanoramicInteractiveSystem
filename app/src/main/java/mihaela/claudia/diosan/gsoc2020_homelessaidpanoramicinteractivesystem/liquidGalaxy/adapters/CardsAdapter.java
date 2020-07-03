package mihaela.claudia.diosan.gsoc2020_homelessaidpanoramicinteractivesystem.liquidGalaxy.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import org.w3c.dom.Text;

import java.util.List;

import mihaela.claudia.diosan.gsoc2020_homelessaidpanoramicinteractivesystem.R;
import mihaela.claudia.diosan.gsoc2020_homelessaidpanoramicinteractivesystem.adapters.HomelessAdapter;
import mihaela.claudia.diosan.gsoc2020_homelessaidpanoramicinteractivesystem.liquidGalaxy.utils.Cards;

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.CardsAdapterHolder>  {
    private List<Cards> cardsList;
    private OnItemClickListener mListener;


    public interface OnItemClickListener{
        void onItemClick(int position);
    }

   public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
   }

    public CardsAdapter(List<Cards> cardsList) {
        this.cardsList = cardsList;
    }

    @NonNull
    @Override
    public CardsAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_design, parent, false);
        return new CardsAdapterHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CardsAdapterHolder holder, int position) {
        Cards card = cardsList.get(position);
        holder.title.setText(card.getTitle());
        holder.icon.setImageResource(card.getImage());
        holder.cardView.setCardBackgroundColor(card.getBackgroundColor());
          }

    @Override
    public int getItemCount() {
        return cardsList.size();
    }

    static class  CardsAdapterHolder extends RecyclerView.ViewHolder{
        ImageView icon;
        TextView title;
        MaterialCardView cardView;


        public CardsAdapterHolder(@NonNull View itemView,final OnItemClickListener listener) {
            super(itemView);

            icon = itemView.findViewById(R.id.icon_card);
            title = itemView.findViewById(R.id.title_card);
            cardView = itemView.findViewById(R.id.dashboard_cv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
