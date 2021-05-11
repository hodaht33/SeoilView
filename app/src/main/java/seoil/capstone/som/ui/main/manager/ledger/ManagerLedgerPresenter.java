package seoil.capstone.som.ui.main.manager.ledger;

public class ManagerLedgerPresenter implements  ManagerLedgerContract.Presenter{

    private ManagerLedgerContract.View view;

    @Override
    public void setView(ManagerLedgerContract.View view) {
        this.view = view;
    }

    @Override
    public void releaseView() {
        this.view = null;
    }

    @Override
    public void createInteractor() {

    }

    @Override
    public void releaseInteractor() {

    }

    public String getDate(String date) {

        if (date.equals("Sun")) {

            return "일";
        } else if (date.equals("Mon")) {

            return "월";
        } else if (date.equals("Tue")) {

            return "화";
        } else if (date.equals("Wed")) {

            return "수";
        } else if (date.equals("Thu")) {

            return "목";
        } else if (date.equals("Fri")) {

            return "금";
        } else if (date.equals("Sat")) {

            return "토";
        } else {

            return "Error";
        }
    }


    public String getDateQuery(int year, int month, int day) {

        String dateQeury;
        if (day < 10) {

            if (month < 10) {

                dateQeury = "" + year + "-0" + month + "-0" + day;
            } else {

                dateQeury = "" + year + "-" + month + "-0" + day;
            }

        } else {

            if (month < 10) {

                dateQeury = "" + year + "-0" + month + "-" + day;
            } else {

                dateQeury = "" + year + "-" + month + "-" + day;
            }
        }

        return dateQeury;
    }
}
