<?php

// centralisation des appels des 3 api's

/**
 * ApiClient : wrapper autour de cURL pour consommer les APIs REST.
 * Utilisé par tous les services de l'IHM.
 */
class ApiClient {

    /**
     * Envoie une requête GET et retourne le tableau PHP décodé.
     *
     * @param string $url URL complète de l'endpoint (ex: http://localhost:3003/plats)
     * @return array Tableau associatif issu du JSON, vide en cas d'erreur
     */
    public static function get(string $url): array {
        $ch = curl_init($url);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_TIMEOUT, 5);

        $response = curl_exec($ch);
        $httpCode = curl_getinfo($ch, CURLINFO_HTTP_CODE);
        curl_close($ch);


        if ($response === false || $httpCode !== 200) {
            return [];
        }

        return json_decode($response, true) ?? [];
    }

    /**
     * Envoie une requête POST avec un body JSON.
     *
     * @param string $url    URL de l'endpoint
     * @param array  $data   Données à envoyer
     * @return array Réponse décodée
     */
    public static function post(string $url, array $data): array {
        $json = json_encode($data);

        $ch = curl_init($url);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_POST, true);
        curl_setopt($ch, CURLOPT_POSTFIELDS, $json);
        curl_setopt($ch, CURLOPT_HTTPHEADER, [
            'Content-Type: application/json',
            'Content-Length: ' . strlen($json)
        ]);
        curl_setopt($ch, CURLOPT_TIMEOUT, 5);

        $response = curl_exec($ch);
        curl_close($ch);

        return json_decode($response, true) ?? [];
    }
}
