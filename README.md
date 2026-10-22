# 🎯 QuizImages - Quiz de Formes Géométriques sur Android

**QuizImages** est une application Android native développée en **Kotlin**. Elle propose un quiz interactif basé sur la reconnaissance de visuels et formes géométriques (triangle, carré, cercle, étoile, rectangle).

---

## 🚀 Fonctionnalités

- ❓ **Questions à Choix Multiples (QCM)** : Chaque question affiche une forme géométrique sous forme d'image avec 3 propositions de réponses.
- 🟢🔴 **Feedback visuel immédiat** :
  - La bonne réponse est surlignée en vert.
  - En cas d'erreur, la réponse sélectionnée apparaît en rouge et la bonne réponse est également indiquée.
- 📊 **Suivi de la progression** :
  - Affichage du score en temps réel.
  - Barre de progression (`ProgressBar`) dynamique.
- 🏆 **Écran de résultat final** : Résumé du score obtenu à la fin du quiz avec la possibilité de recommencer le quiz à tout moment.
- 📱 **Gestion multi-orientations** : Adapté aux modes **Portrait** et **Paysage** (`layout-land`).
- ♿ **Accessibilité** : Utilisation de descriptions textuelles (`contentDescription`) sur chaque image pour la lecture d'écran.

---

## 🛠️ Stack Technique

- **Langage** : Kotlin
- **SDK Minimum** : API 24 (Android 7.0 Nougat)
- **SDK Cible** : API 37
- **Architecture & UI** :
  - AndroidX AppCompat & ConstraintLayout
  - Material Components
  - XML Layouts (avec gestion portrait/paysage)

---

## 📂 Structure du Projet

```text
QuizImages/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/quizimages/
│   │   │   │   └── MainActivity.kt       # Logique principale du quiz
│   │   │   ├── res/
│   │   │   │   ├── drawable/              # Formes vectorielles (triangle, carré, cercle, étoile, rectangle)
│   │   │   │   ├── layout/                # Layout XML principal (mode portrait)
│   │   │   │   ├── layout-land/           # Layout XML adapté (mode paysage)
│   │   │   │   └── values/                # Thèmes, couleurs et chaînes de caractères
│   │   │   └── AndroidManifest.xml
│   │   └── test/                          # Tests unitaires
│   └── build.gradle.kts                   # Configuration du module app
├── build.gradle.kts                       # Build script racine
└── settings.gradle.kts                    # Paramètres Gradle
```

---

## ⚙️ Installation & Exécution

1. **Cloner le projet** ou l'ouvrir directement dans **Android Studio**.
2. **Synchroniser le projet avec Gradle** (`Gradle Sync`).
3. Connecter un appareil Android physique ou lancer un **Émulateur Android** (API 24+).
4. Cliquer sur **Run 'app'** (`Shift + F10`) pour compiler et exécuter l'application.

---

## 🎮 Formes présentées dans le Quiz

| Forme | Description |
| :--- | :--- |
| 📐 **Triangle** | Triangle bleu |
| ⬛ **Carré** | Carré rouge |
| 🟢 **Cercle** | Cercle vert |
| ⭐ **Étoile** | Étoile jaune |
| 🟧 **Rectangle** | Rectangle orange |

---

## 📝 Licence

Projet réalisé dans un cadre éducatif / démonstration.
