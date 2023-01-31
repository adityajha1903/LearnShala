package com.example.adi.learnshala

import com.example.adi.learnshala.db.WorkshopEntity

object Constants {

    const val USER_NAME = "user_name"
    const val WORKSHOP_NOT_REGISTERED = 0
    const val WORKSHOP_REGISTERED = 1
    const val WORKSHOP_ADDITION = "workshop_addition"
    const val FIRST_START = "first_start"

    fun getAllWorkshops(): List<WorkshopEntity> {

        val list = ArrayList<WorkshopEntity>()

        list.add(WorkshopEntity(id = 0, title = "Algorithm, Intro to Machine Learning", location = "Delhi", from = "02 Feb 2023", to = "07 Feb 2023", organisation = "KATUSHA", description = "This course provides a robust foundation on machine learning concepts and applications. This course is designed for students who have little to no technical background, yet are committed to venture into the AI space."))
        list.add(WorkshopEntity(id = 1, title = "Abacus", location = "Online", from = " 07 Mar 2023", to =  "12 Mar 2023", organisation = "LEARN CLUE", description = "Abacus is an ancient mathematical tool used for calculations.A math device is a straightforward gadget you can use to make manual numerical estimations by sliding counters along columns of wires set inside a casing."
        ))
        list.add(WorkshopEntity(id = 2, title = "PMP certification workshop", location = "Mumbai", from = "12 Mar 2023", to = "20 Mar 2023", organisation = "PROTHOUGHTS SOLUTION", description = "PMP Certification Workshop in Mumbai arranged with ProThoughts Solutions is aimed at facilitating the complete and comprehensive studies necessary for appearing for and clearing the Project Management Professional certification examination."))
        list.add(WorkshopEntity(id = 3, title = "Coding workshop", location = "Kolkata", from = "21 Mar 2023", to = "22 Mar 2023", organisation = "GO GLOBAL WAYS", description = "Our education program is meticulously designed for each grade and religiously follows Bloom’s Taxonomy and PBL approach integrated with a 3C learning structure—Creativity, Capacity, and Curiosity."))
        list.add(WorkshopEntity(id = 4, title = "Start a startup", location = "Delhi", from = "07 May 2023", to = "10 May 2023", organisation = "STIRRING MINDS", description = "Are you looking for a kick start? Or maybe just not sure if your idea is worth a shot? Well, the START A STARTUP -MEETUP 13.0 is the place for you."))
        list.add(WorkshopEntity(id = 5, title = "Resin clock workshop", location = "Surat", from = "20 Jun 2023", to = "21 Jun 2023", organisation = "RESIN REIGN", description = "Learn resin clock in this workshop all materials will be provided"))
        list.add(WorkshopEntity(id = 6, title = "Project management workshop", location = "Hyderabad", from = "30 Jul 2023", to = "07 Aug 2023", organisation = "PROTHOUGHTS SOLUTION", description = "PMP Training in Hyderabad is in high demand as most of the top MNCs & software IT organizations are based out of Hyderabad. The growing need of updating & growing oneself with the latest practices is higher than ever."))
        list.add(WorkshopEntity(id = 7, title = "Software testing", location = "Hyderabad", from = "28 Aug 2022", to = "15 Sep 2022", organisation = "TECH ADDA", description = "Want to start your carrier as a Software Tester? Be an expert Software Testing Engineer through our experienced trainers!"))
        list.add(WorkshopEntity(id = 8, title = "Passion for expansion", location = "Online", from = "11 Feb 2023", to = "15 Feb 2023", organisation = "GROWKARS", description = "Passion for Expansion is now open for February participants."))
        list.add(WorkshopEntity(id = 9, title = "Photography workShop", location = "Delhi", from = "05 Mar 2023", to = "10 Mar 2023", organisation = "IRINA VATEL", description = "No prior experience required, For ages 16+, No professional equipments mandatory."))
        list.add(WorkshopEntity(id = 10, title = "Pottery workshop", location = "Bengaluru", from = "30 Mar 2023", to = "23 Apr 2023", organisation = "LAHE POTTERY", description = "You can sign up for a single trial class or sign up for a session of 10 classes. 6 sessions of those will be facilitator led and 4 sessions will be practice sessions where you can practice on your own."))
        list.add(WorkshopEntity(id = 11, title = "Stand-up comedy workshop", location = "Online", from = "04 Mar 2023", to = "11 Mar 2023", organisation = "KUNAL RAO", description = "This is a two-hour bootcamp workshop, online on Zoom. Along with the basics of joke writing, you will learn the to 5 hacks for joke-writing!"))
        list.add(WorkshopEntity(id = 12, title = "Art from ashes", location = "Chennai", from = "19 Feb 2023", to = "28 Feb 2023", organisation = "THE CLAY SCHOOL", description = "This February let's shower the love to our mother Earth and promote the thought of reducing waste and recycling. Join us for a fun filled and interactive workshop to make an Art from waste materials also know more about how you can contribute for the cause."))
        list.add(WorkshopEntity(id = 13, title = "Heritage as stories", location = "Kochi", from = "12 Mar 2023", to = "30 Mar 2023", organisation = "VIKRAM SRIDHAR", description = "An engaging and interactive online workshop to explore how we can look at Heritage as a way to connect to our cultural history and environment through stories. A rich country like India has so many facets to it when we look at it as a rich heritage and not just as history."))
        list.add(WorkshopEntity(id = 14, title = "Doctor of business administration", location = "Online", from = "20 Apr 2023", to = "15 May 2023", organisation = "GOLDEN GATE UNIVERSITY", description = "A Doctor of Business Administration (DBA) is the highest qualification in the field of Business and Management. The DBA program is designed for working professionals who want to further their career with a doctorate."))

        return list.toList()
    }
}