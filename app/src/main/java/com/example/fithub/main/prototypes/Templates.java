package com.example.fithub.main.prototypes;

import com.example.fithub.main.prototypes.data.DatabaseManager;
import com.example.fithub.main.prototypes.data.ExerciseData;
import com.example.fithub.main.prototypes.data.MuscleGroup;
import com.example.fithub.main.prototypes.data.PlanEntry;
import com.example.fithub.main.prototypes.data.TrainingPlan;

import java.util.ArrayList;
import java.util.List;

/** Helper class to create templates or reset exercises to factory settings. */
public class Templates {

  /**
   * Creates the exercise templates that are hardcoded.
   *
   * @return List with template exercises
   */
  public List<ExerciseData> createExerciseDataTemplates() {
    final List<ExerciseData> exerciseData = new ArrayList<>();

    final ExerciseData emptyExerciseDataTemplate =
        new ExerciseData(
            1,
            "Hier Übungsnamen eingeben...",
            "Hier Anleitung einfügen...",
            "Hier Bild URL einfügen...",
            "Hier Video Url einfügen...");
    exerciseData.add(emptyExerciseDataTemplate);

    final ExerciseData chinupsExerciseDataTemplate =
        new ExerciseData(
            2,
            "Klimmzug",
            "Wichtigste Grundregel beim Klimmzug: Er sollte immer im vollen Bewegungsradius ausgeführt werden.\n"
                + "\n"
                + "Das bedeutet für die Ausführung:\n"
                + "\n"
                + " - Im kompletten passiven Hang starten, die Muskulatur ist entspannt.\n"
                + "\n"
                + " - Schulterblätter fixieren, indem sie nach hinten unten gezogen werden, und so in den aktiven Hang wechseln.\n"
                + "\n"
                + " - Bauch anspannen und aus der Kraft der Arme und des oberen Rückens nach oben ziehen, bis die Stange vor dem Brustbein ist.\n"
                + "\n"
                + " - Kurz halten und kontrolliert wieder ablassen.\n"
                + "\n"
                + " - Aus dem passiven Hang erneut starten."
                + "\n",
            "https://www.inspireusafoundation.org/wp-content/uploads/2021/12/the-jumping-pull-up-1024x523.png",
            "https://youtu.be/T78xCiw_R6g");
    exerciseData.add(chinupsExerciseDataTemplate);

    final ExerciseData squatsExerciseDataTemplate =
        new ExerciseData(
            3,
            "Kniebeugen",
            "Kniebeugen sind eine beindominante Übung. Das heißt, die Kraft kommt vor allem aus den Beinen und dem Gesäß.\n"
                + "\n"
                + "Nichts desto trotz musst du deine Bauchmuskeln anspannen, um einem Hohlkreuz entgegen zu wirken und die Wirbelsäule zu schonen.\n"
                + "\n"
                + "Bei der Ausführung kommt es auf ein paar Feinheiten an:\n"
                + "\n"
                + " - Die Füße sind mindestens schulterbreit aufgestellt, die Belastung ist auf dem kompletten Fuß, die Knie sind leicht nach außen rotiert\n"
                + "\n"
                + " - Die Langhantel liegt auf dem Trapezius, also eher auf den Schultern als im Nacken\n"
                + "\n"
                + " - Greife die Hantel so, dass die Handflächen nach vorne zeigen, die Handgelenke sind gerade, die Ellenbogen unter der Hantel\n"
                + "\n"
                + " - Spanne den Bauch an, ziehe die Schulterblätter nach hinten unten, strecke die Brust raus\n"
                + "\n"
                + " - Atme tief ein, baue einen Druck im Bauchinnenraum auf, halte die Luft an und beginne, in die Knie zu gehen\n"
                + "\n"
                + " - Senke dich kontrolliert und langsam ab, die Knie zeigen dabei immer leicht nach außen\n"
                + "\n"
                + " - Unten angekommen kurz halten und kontrolliert wieder aufrichten, dabei vom kompletten Fuß abdrücken und aus dem Mund kräftig ausatmen"
                + "\n",
            "https://s3.amazonaws.com/prod.skimble/assets/2288721/image_iphone.jpg",
            "https://youtu.be/huVujjfzphI");
    exerciseData.add(squatsExerciseDataTemplate);

    final ExerciseData deadliftExerciseDataTemplate =
        new ExerciseData(
            4,
            "Kreuzheben",
            "Kreuzheben ist eine hüftdominante Übung. Das heißt, die Kraft kommt weniger aus den Beinen als bei Kniebeugen und stärker aus der Hüfte.\n"
                + "\n"
                + "Die Grundbewegung beim Kreuzheben ist ganz simpel: Du schiebst dein Becken nach vorne und hinten. Dabei kommt es natürlich auf ein paar Feinheiten an:\n"
                + "\n"
                + " - Du stehst schulterbreit vor der Langhantel, die Knie sind leicht gebeugt. Greife die Langhantel, deine Hände befinden sich rechts und links von deinen Schienbeinen, die Handflächen zeigen zu dir.\n"
                + "\n"
                + " - Ziehe die Schulterblätter nach hinten unten, habe eine stolze Brust.\n"
                + "\n"
                + " - Einatmen, Spannung in den Bauch bringen, Rücken gerade halten.\n"
                + "\n"
                + " - Bringe die Langhantel auf Hüfthöhe, indem du dein Becken nach vorne schiebst. Atme aus.\n"
                + "\n"
                + " - Das Absenken erfolgt, indem du dein Becken wieder nach hinten in die Ausgangsposition bringst. Die Langhantel bleibt nah an deinen Beinen. Deine Knie sind immer leicht gebeugt."
                + "\n",
            "https://i.pinimg.com/originals/a5/42/e9/a542e970fc61d9442e35efb07380262d.jpg",
            "https://youtu.be/FoJPVSL002E");
    exerciseData.add(deadliftExerciseDataTemplate);

    final ExerciseData benchpressExerciseDataTemplate =
        new ExerciseData(
            5,
            "Bankdrücken",
            " - Lege dich rücklings auf eine Flachbank, so dass sich die Langhantelstange in der Halterung auf Höhe deiner Augen befindet.\n"
                + "\n"
                + " - Die Füße fest auf den Boden stellen oder die Beine nacheinander hoch nehmen, anwinkeln und die unterschenkel kreuzen.\n"
                + "\n"
                + " - Die Hantelstange mit beiden Händen greifen. Die Griffbreite hängt zwar davon ab, welchen Teil der Brustmuskulatur du besonders stärken möchtest. In der Grundposition bilden Unterarme und Oberarme ungefähr einen 90-Grad-Winkel in den Ellenbogen.\n"
                + "\n"
                + " - Spanne Bauch und Rückenstrecker an, dadurch kann sogar ein leichtes Hohlkreuz entstehen. Zusätzlich ziehst du die Schulterblätter nach hinten, um die Schultergelenke zu stabilisieren.\n"
                + "\n"
                + " - Das Gewicht auf Höhe der Brust oder den oberen Bereich der Bauchmuskeln absenken. Die Rückenstrecker sind die ganze Zeit angespannt. Die Hantel bis auf die Brust herablassen.\n"
                + "\n"
                + " - Nach kurzer Pasue drückst du die Hantel mit Kraft nach oben, wobei du deine Arme streckst, aber nicht komplett durchstreckst. Behalte immer die Kontrolle über das Gewicht!\n"
                + "\n"
                + " - Am oberen Punkt der Bewegung angekommen, führst du die Hantel sofort wieder langsam in die Rückbewegung.",
            "https://s3.amazonaws.com/prod.skimble/assets/2289478/image_iphone.jpg",
            "https://youtu.be/LnhpKTXeIeg");
    exerciseData.add(benchpressExerciseDataTemplate);

    final ExerciseData reverseFlyExerciseDataTemplate =
        new ExerciseData(
            6,
            "Reverse Fly",
            "Setze dich mit dem Blick Richtung Gerät an die Butterfly Reverse-Maschine. Deine Brust stützt sich gegen das vertikal angebrachte Polster, deine Füße stehen fest auf dem Boden, während deine Unter- und Oberschenkel einen Winkel von etwa 90 Grad bilden. Jetzt umgreifst du die Griffstangen. Deine Arme sollten im Ellenbogengelenk leicht geknickt und deine Hände auf Höhe der Schulter sein. Andernfalls musst du das Sitzpolster nach unten bzw. oben korrigieren, so lange bis Hände und Schultergelenke auf einer Höhe sind. Dein Rücken ist durchgestreckt, dein Blick gerade aus gerichtet und deine Bauchmuskulatur angespannt.\n"
                + "\n"
                + "Während du ausatmest, führst du die Stangen mit den fast ausgestreckten Armen so weit wie möglich nach hinten. Hierbei ist es wichtig, dass deine Arme unverändert bleiben und du nur mit der hinteren Schultermuskulatur und dem Trapezmuskel arbeitest. Anschließend atmest du ein und führst die Stangen wieder langsam nach vorne zum Gerät.",
            "https://qph.cf2.quoracdn.net/main-qimg-6138a1e659846d90e1e9eee14a679996-lq",
            "https://youtu.be/IMirNGqcktY");
    exerciseData.add(reverseFlyExerciseDataTemplate);

    final ExerciseData militaryPressExerciseDataTemplate =
        new ExerciseData(
            7,
            "Überkopfdrücken",
            "Du hältst eine Langhantel direkt auf der Höhe deiner Brust. \n"
                + "Deine Muskulatur ist angespannt, sodass du beim Ausatmen die Langhanteln nach oben drückst. \n"
                + "Die Bewegung sollte kontrolliert und ohne Schwung erfolgen. \n"
                + "Beim Einatmen führst du die Langhantel wieder auf deine Brust zurück, wobei du ebenfalls auf Balance und Stabilität achten solltest.\n"
                + "",
            "https://i.pinimg.com/originals/d4/e8/d9/d4e8d90b9664361c76853b420101f773.jpg",
            "https://youtu.be/8gVEdhS5AEs");
    exerciseData.add(militaryPressExerciseDataTemplate);

    final ExerciseData crunchExerciseDataTemplate =
        new ExerciseData(
            8,
            "Crunch",
            "Lege dich in Rückenlage auf den Boden oder auf die Trainingsmatte und winkle deine Beine an, so dass deine Fußsohlen flach auf dem Boden stehen. Der Abstand zwischen deinen Beinen ist maximal hüftbreit. Deine Hände berühren mit den Fingerspitzen rechts und links den Kopf und deine Ellenbogen zeigen zur rechten und linken Seite. Dein Kopf ist in seiner natürlichen Position, mit Blick nach schräg oben. Das Kinn liegt nicht auf der Brust und der Kopf sollte nicht im Nacken liegen.\n"
                + "\n"
                + "Jetzt hebst du die Brust vom Boden ab und bewegst sie in Richtung der Kniegelenke. Dabei wird der Oberkörper leicht gekrümmt (daher: crunch). Während dieser Bewegung atmest du aus. Im Anschluss atmest du ein und senkst den Oberkörper wieder nach hinten ab. Um die Muskelspannung aufrecht zu erhalten, senkst du den Oberkörper nicht völlig ab, sondern hältst Kopf, Arme und Schultern in der Luft. Die Schulterblätter dürfen den Boden erst wieder berühren, wenn die Übung abgeschlossen ist!\n"
                + "\n"
                + "Während der gesamten Übungsdurchführung bleibt deine Kopf- und Armstellung völlig unverändert. Lediglich dein Oberkörper bewegt sich, um die Crunch-Bewegung auszuführen.",
            "https://julienquaglierini.com/wp-content/uploads/2015/11/crunch-au-sol.jpg",
            "https://youtu.be/Xyd_fa5zoEU");
    exerciseData.add(crunchExerciseDataTemplate);

    final ExerciseData rowExerciseDataTemplate =
        new ExerciseData(
            9,
            "Rudern",
            "Greife die Langhantel schulterbreit im Untergriff mit beiden Händen und stelle deine Füße schulterbreit auseinander. Nachdem du über die Kreuzheben-Technik die Langhantel aufgenommen hast, beugst du deine Beine leicht und beugst den Oberkörper so weit nach vorne, dass du in einem 45 Grad-Winkel nach vorne gebeugt stehst. Dein Rücken befindet sich in einem leichten Hohlkreuz mit Körperspannung, dein Po ist nach hinten und deine Brust nach vorne herausgestreckt.\n"
                + "\n"
                + "Während du ausatmest, ziehst du die Langhantel bis zu deinen unteren Bauchmuskeln. Achte darauf, dass dein unterer Rücken in seiner Postion bleibt und nicht gebeugt wird. Danach führst du während deines Einatmens die Langhantel langsam zurück in die Ausgangsposition.",
            "https://training.fit/wp-content/uploads/2020/02/rudern-langhantel.png",
            "https://youtu.be/TZLCvXbej_Q");
    exerciseData.add(rowExerciseDataTemplate);

    final ExerciseData legPressExerciseDataTemplate =
        new ExerciseData(
            10,
            "Beinpresse",
            "Eine der bekanntesten und wirkungsvollsten Übungen an einem Kraftgerät ist die Beinpresse. Fast jedes Fitnessstudio, jedes Leistungszentrum und jeder Physiotherapeut hat solch eine Maschine, weshalb unumstritten sein dürfte, dass dieses Fitnessgerät eines der besten ist. Es gibt verschiedene Varianten dieses Geräts, die sich aber in ihrer Wirkungsweise kaum unterscheiden.\n"
                + "\n"
                + "Trainiert werden hier fast sämtliche Muskeln der Beine und zudem der Po. Im Detail sind dies die Beinstrecker (musculus quadrizeps femoris) und Beinbeuger (musculus biceps femoris) sowie der große Gesäßmuskel (musculus gluteus maximus). Mit beansprucht werden je nach Fußstellung die Adduktoren (musculus adductor) und die Wadenmuskulatur (musculus gastrocnemius).",
            "https://www.inspireusafoundation.org/wp-content/uploads/2021/10/leg-press-machine-1024x430.png",
            "https://youtu.be/xCQ-FY_bj9E");
    exerciseData.add(legPressExerciseDataTemplate);

    return exerciseData;
  }

  /**
   * Add some exercises to the training plan templates.
   *
   * @return training plan with entries.
   */
  public List<PlanEntry> createPlanEntryTemplates() {
    final List<PlanEntry> planEntryList = new ArrayList<>();

    final List<ExerciseData> exerciseDataList = createExerciseDataTemplates();

    planEntryList.add(
        new PlanEntry(1, "0kg", "0x0", exerciseDataList.get(1).getExerciseDataId(), 1));
    planEntryList.add(
        new PlanEntry(2, "5kg", "3x12", exerciseDataList.get(1).getExerciseDataId(), 1));
    planEntryList.add(
        new PlanEntry(3, "5kg", "3x15", exerciseDataList.get(2).getExerciseDataId(), 1));

    return planEntryList;
  }

  /**
   * create training plans with entries.
   *
   * @return list of training plans.
   */
  public List<TrainingPlan> createTrainingPlanTemplates() {
    final List<TrainingPlan> trainingPlanList = new ArrayList<>();

    TrainingPlan trainingPlanTemplate1 = new TrainingPlan(1, "Trainingsplan 1");
    trainingPlanList.add(trainingPlanTemplate1);

    TrainingPlan trainingPlanTemplate2 = new TrainingPlan(2, "Trainingsplan 2");
    trainingPlanList.add(trainingPlanTemplate2);

    return trainingPlanList;
  }

  public void addMuscleGroups() {
    String[] muscleGroupArray = {"Beine", "Brust", "Arme", "Schultern", "Bauch", "Rücken"};

    for (int i = 0; i < muscleGroupArray.length; i++) {
      DatabaseManager.appDatabase.muscleGroupDao().insert(new MuscleGroup(i, muscleGroupArray[i]));
    }
  }
}
