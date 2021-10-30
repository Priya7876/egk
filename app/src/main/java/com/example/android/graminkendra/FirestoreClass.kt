package com.example.android.graminkendra

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class FirestoreClass {
    private val mStore= FirebaseFirestore.getInstance()


    fun getCurrentUserId():String{
        var currentUSer= FirebaseAuth.getInstance().currentUser
        var currentUserId=""
        if(currentUSer!=null){
            currentUserId=currentUSer.uid
        }
        return currentUserId
    }
    fun createComplaint(activity:addComplaint,complaint12 :complaint){
        mStore.collection("boards").document().set(complaint12, SetOptions.merge()).addOnSuccessListener {
            Log.e ("BOARD SUCCESSFULLY","Complaint Added successfully")

            Toast.makeText(activity,"Complaint added", Toast.LENGTH_SHORT).show()
            activity.boardCreateSuccess()
        }.addOnFailureListener {
            activity.hideDialog()
            Log.e ("Addition failed","OOPS,ERROR ARE THERE")

        }
    }
    fun getBoardList(activity:infoC){
        mStore.collection("boards").get().addOnSuccessListener {
                    result->
                Log.e("getSuccess","getSuccess")
                val boarList:ArrayList<complaint> =ArrayList()
                for(document in result){
                    val board=document.toObject(complaint::class.java)!!

                    boarList.add(board)
                }
                activity.populateRecyclerView(boarList)
            }.addOnFailureListener {
                activity.hideDialog()
                Log.e("error in getList","oops have error in getting list")
            }
    }
    fun createMeet(activity:additionOfEvent,meet :meeting){
        mStore.collection("meetings").document().set(meet, SetOptions.merge()).addOnSuccessListener {
            Log.e ("Meet  SUCCESSFULLY","Event  Added successfully")

            Toast.makeText(activity,"Event added", Toast.LENGTH_SHORT).show()
            activity.MeetCreateSuccess()
        }.addOnFailureListener {
            activity.hideDialog()
            Log.e ("Addition meet failed","OOPS,ERROR ARE THERE")

        }
    }
    fun getMeetList(activity:infoM){
        mStore.collection("meetings").get()
            .addOnSuccessListener {
                    result->
                Log.e("getSuccess","getSuccess")
                var meetList:ArrayList<meeting> =ArrayList()
                for(document in result){
               var meetEvent=document.toObject(meeting::class.java)!!
                  meetList.add(meetEvent)


               }
                activity.populateRecyclerView(meetList)
            }.addOnFailureListener {
                activity.hideDialog()
                Log.e("error in getList","oops have error in getting list")
            }
    }

    fun createScheme(activity:additionOfSchemas,schemes :scheme){
        mStore.collection("schemes").document().set(schemes, SetOptions.merge()).addOnSuccessListener {
            Log.e ("Meet  SUCCESSFULLY","Event  Added successfully")

            Toast.makeText(activity,"Event added", Toast.LENGTH_SHORT).show()
            activity.SchemeCreateSuccess()
        }.addOnFailureListener {
            activity.hideDialog()
            Log.e ("Addition meet failed","OOPS,ERROR ARE THERE")

        }
    }
    fun getSchemeList(activity:infoS){
        mStore.collection("schemes").get()
            .addOnSuccessListener {
                    result->
                Log.e("getSuccess","getSuccess")
                var SchemeList:ArrayList<scheme> =ArrayList()
                for(document in result){
                    var schemeEvent=document.toObject(scheme::class.java)!!
                    SchemeList.add(schemeEvent)


                }
                activity.populateRecyclerView(SchemeList)
            }.addOnFailureListener {
                activity.hideDialog()
                Log.e("error in getList","oops have error in getting list")
            }
    }
    fun registerUser(activity :signActivity,userInfo: User){
        mStore.collection("User").document(getCurrentUserId()).set(
            userInfo, com.google.firebase.firestore.SetOptions.merge()
        ).addOnSuccessListener {
            activity.profileUpdateSuccess()

        }

    }
    fun updateUserProfileData(activity: myProfileActivity,userHashMap:HashMap<String,Any>)
    {
        mStore.collection("User").document(getCurrentUserId())
            .update(userHashMap).addOnSuccessListener {
                Log.e("updated message","Data is updated successfully")
                Toast.makeText(activity
                    , "Data is updated successfully",
                    Toast.LENGTH_SHORT).show()
                activity.profileUpdateSuccess()
            }.addOnFailureListener{
                    e->
                activity.hideDialog()
                Log.e("updation is failed","Error")

            }

    }


    fun UsweFireData(activity: Activity,readBoardList:Boolean=false){
        mStore.collection("User").document(getCurrentUserId()).get(

        ).addOnSuccessListener {document ->
            val user=document.toObject(User::class.java)!!
            when(activity){

                is information ->{
                    activity.updateNavigationBar(user)
                }
is myProfileActivity ->{
    activity.updateMyProfile(user)
}
            }
        }.addOnFailureListener {
                e->
            when(activity){

                is information ->{
                    activity.hideDialog()

                }
                is myProfileActivity ->{
                    activity.hideDialog()
                }

            }
            Log.e("Error","error in signing in fireBase")
        }

    }

    fun createAsha(activity:addAsha,Asha:asha){
        mStore.collection("AshaWorker").document().set(Asha, SetOptions.merge()).addOnSuccessListener {
            Log.e ("Asha SUCCESSFULLY","Event  Added successfully")

            Toast.makeText(activity,"Event added", Toast.LENGTH_SHORT).show()
            activity.AshaCreateSuccess()
        }.addOnFailureListener {
            activity.hideDialog()
            Log.e ("Addition meet failed","OOPS,ERROR ARE THERE")

        }

    }
    fun getAshaList(activity:ashaWorker){
        mStore.collection("AshaWorker").get()
            .addOnSuccessListener {
                    result->
                Log.e("getSuccess","getSuccess")
                var AshaList:ArrayList<asha> =ArrayList()
                for(document in result){
                    var asha1=document.toObject(asha::class.java)!!
                    AshaList.add(asha1)


                }
                activity.populateRecyclerView(AshaList)
            }.addOnFailureListener {
                activity.hideDialog()
                Log.e("error in getList","oops have error in getting list")
            }
    }
fun createVaccination(activity:addVaccinationCentre,vaccinationG:vaccination){
    mStore.collection("Vaccination").document().set(vaccinationG, SetOptions.merge()).addOnSuccessListener {
        Log.e ("VaccinationSuccess","Event  Added successfully")

        Toast.makeText(activity,"Event added", Toast.LENGTH_SHORT).show()
       activity.VaccinationCreateSuccess()
    }.addOnFailureListener {
        activity.hideDialog()
        Log.e ("Addition centre failed","OOPS,ERROR ARE THERE")

    }

}
    fun getVaccinationList(activity:vaccinationInfo){
        mStore.collection("Vaccination").get()
            .addOnSuccessListener {
                    result->
                Log.e("getSuccess","getSuccess")
                var VaccinationList:ArrayList<vaccination> =ArrayList()
                for(document in result){
                    var vaccinatiom=document.toObject(vaccination::class.java)!!
                    VaccinationList.add(vaccinatiom)


                }
                activity.populateRecyclerView(VaccinationList)
            }.addOnFailureListener {
                activity.hideDialog()
                Log.e("error in getList","oops have error in getting list")
            }
    }
    fun createCovidCaringCentre(activity:addCovidCentre,CaringG:caringCentre){
        mStore.collection("CaringCentre").document().set(CaringG, SetOptions.merge()).addOnSuccessListener {
            Log.e ("CovidSuccess","Event  Added successfully")

            Toast.makeText(activity,"Event added", Toast.LENGTH_SHORT).show()
            activity.CovidCaringCreateSuccess()
        }.addOnFailureListener {
            activity.hideDialog()
            Log.e ("Addition centre failed","OOPS,ERROR ARE THERE")

        }

    }
    fun getCovidCaringList(activity:covidCentreInfo){
        mStore.collection("CaringCentre").get()
            .addOnSuccessListener {
                    result->
                Log.e("getSuccess","getSuccess")
                var CaringCentreList:ArrayList<caringCentre> =ArrayList()
                for(document in result){
                    var caringCentre1=document.toObject(caringCentre::class.java)!!
                    CaringCentreList.add(caringCentre1)


                }
                activity.populateRecyclerView(CaringCentreList)
            }.addOnFailureListener {
                activity.hideDialog()
                Log.e("error in getList","oops have error in getting list")
            }
    }
    fun createPhoneNumber(activity:AddPhoneNumber,Phone:phoneFormat){
        mStore.collection("PhoneList").document().set(Phone, SetOptions.merge()).addOnSuccessListener {
            Log.e ("PhoneList","Event  Added successfully")

            Toast.makeText(activity,"Event added", Toast.LENGTH_SHORT).show()
            activity.PhoneListCreated()
        }.addOnFailureListener {
            activity.hideDialog()
            Log.e ("Addition centre failed","OOPS,ERROR ARE THERE")

        }

    }

    fun getPhoneList(activity:phoneNumberList){
        mStore.collection("PhoneList").get()
            .addOnSuccessListener {
                    result->
                Log.e("getSuccess","getSuccess")
                var PhoneList:ArrayList<phoneFormat> =ArrayList()
                for(document in result){
                    var phoneNumber1=document.toObject(phoneFormat::class.java)!!

                    PhoneList.add(phoneNumber1)

                }
                activity.populateRecyclerView(PhoneList)
            }.addOnFailureListener {
                activity.hideDialog()
                Log.e("error in getList","oops have error in getting list")
            }
    }
    fun createProject(activity:addOnGoingProject,Project:project){
        mStore.collection("ProjectList").document().set(Project, SetOptions.merge()).addOnSuccessListener {
            Log.e ("ProjectList","Event  Added successfully")

            Toast.makeText(activity,"Event added", Toast.LENGTH_SHORT).show()
          activity.ProjectCreationSuccessfull()
        }.addOnFailureListener {
            activity.hideDialog()
            Log.e ("Addition project failed","OOPS,ERROR ARE THERE")

        }

    }
    fun getProjectList(activity:projectInfo){
        mStore.collection("ProjectList").get()
            .addOnSuccessListener {
                    result->
                Log.e("getSuccess","getSuccess")
                var PROJECTList:ArrayList<project> =ArrayList()
                for(document in result){
                    var projectNumber1=document.toObject(project::class.java)!!

                   projectNumber1.documentId=document.id
                   PROJECTList.add(projectNumber1)

                }
                activity.populateRecyclerView(PROJECTList)
            }.addOnFailureListener {
                activity.hideDialog()
                Log.e("error in getList","oops have error in getting list")
            }
    }
    fun updatePROJECTData(activity: editDetail,userHashMap:HashMap<String,Any>,Project1:project)
    {
        mStore.collection("ProjectList").document(Project1.documentId)
            .update(userHashMap).addOnSuccessListener {
                Log.e("updated message","Data is updated successfully")
                Toast.makeText(activity
                    , "Data is updated successfully",
                    Toast.LENGTH_SHORT).show()
                activity.ProjectUpdateSuccess()

            }.addOnFailureListener{
                    e->
                activity.hideDialog()
                Log.e("updation is failed","Error")

            }

    }


}
