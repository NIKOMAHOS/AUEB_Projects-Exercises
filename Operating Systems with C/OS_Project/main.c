#include "defines.h"

//resources variables
uint cooks = N_cook;            //number of cooks
uint ovens = N_oven;            //number of ovens
uint packers = N_packer;        //number of packers    
uint deliverers = N_deliverer;  //number of deliverers

//counter and sum variables
uint profit;                //total profit
uint plain;                 //counter of plain pizzas sold
uint special;               //counter of special pizzas sold
uint succeeded;             //counter of succeeded orders
uint failed;                //counter of failed orders
uint happy;                 //counter of happy customers
uint disappointed;           //counter of disappointed customers

//time variables
double total_waiting_time;
double total_cooling_time;
double max_waiting_time = -1;
double max_cooling_time = -1;
double min_waiting_time = BILLION;
double min_cooling_time = BILLION;
double cooling_time;        
double waiting_time;
double failed_time;
double succeeded_time;

//resources mutexes
pthread_mutex_t cooks_count_mutex;          //locks cooks
pthread_mutex_t ovens_count_mutex;          //locks ovens
pthread_mutex_t packers_count_mutex;        //locks packers
pthread_mutex_t deliverers_count_mutex;     //locks deliverers

//variables mutexes
pthread_mutex_t pizza_count_mutex;
pthread_mutex_t profit_mutex;
pthread_mutex_t satisfied_mutex;
pthread_mutex_t lockscreen_mutex;

//time mutexes
pthread_mutex_t total_waiting_time_mutex;
pthread_mutex_t total_cooling_time_mutex;
pthread_mutex_t max_waiting_time_mutex;
pthread_mutex_t max_cooling_time_mutex;
pthread_mutex_t min_waiting_time_mutex;
pthread_mutex_t min_cooling_time_mutex;

//resources condition variables
pthread_cond_t cooks_cond;              //condition variable for cooks
pthread_cond_t ovens_cond;              //condition variable for ovens
pthread_cond_t packers_cond;            //condition variable for packers
pthread_cond_t deliverers_cond;         //condition variable for deliverers

uint N_cust;            //number of customers
uint seed;

//EPISHS AN H MERA HTAN KERDOFORA BAZONTAS KOSTH STOYS POROYS KAI BLEPOBTAS ME TA SYNOLIKA ESODA.

void *order(void *order_id){
    uint id = *(int *) order_id;
    uint loc_seed = seed*(id);

    //time
    struct timespec start_time;  //order arrives
    struct timespec prep_time;   //preparation
    struct timespec bake_time;   //in the oven
    struct timespec pack_time;  //packing
    struct timespec deliver_time;   //delivering
    struct timespec stop_time; //arrived
    struct timespec failed_timespec;
    struct timespec succeeded_timespec;

    clock_get_time_check(&start_time);
    //printf("\nH paraggelia %d eftase\n", id);
    //printf("\nDokimh xrewshs gia paraggelia %d.\n", id);
    uint time_payment = rand_generator(&loc_seed, T_paymentlow, T_paymenthigh);
    //printf("\nTIME PAYMENT: %d\n", time_payment);
    sleep(time_payment);
    uint success = rand_generator(&loc_seed, 0, 100);
    if(success <= P_fail){
        clock_get_time_check(&failed_timespec);
        failed_time = get_time_difference(&start_time, &failed_timespec);
        //printf("\nFailed Time: %f\n", failed_time);
        mutex_lock(&lockscreen_mutex);
        //printf("===================================================================");
        printf("\nOrder #%d cancelled approximately %.3f %s after being received.\n", id, round(failed_time), (round(failed_time) == 1) ? "minute" : "minutes");
        //printf("===================================================================");
        mutex_unlock(&lockscreen_mutex);
        failed++;
        pthread_exit(&id); 
    }
    clock_get_time_check(&succeeded_timespec);
    succeeded_time = get_time_difference(&start_time, &succeeded_timespec);
    //printf("\nSucceeded Time: %f\n", succeeded_time);
    mutex_lock(&lockscreen_mutex);
    //printf("===================================================================");
    printf("\nOrder #%d approved approximately %.3f %s after being received.\n", id, round(succeeded_time), (round(succeeded_time) == 1) ? "minute" : "minutes");
    //printf("===================================================================");
    mutex_unlock(&lockscreen_mutex);
    succeeded++;

    //HOW MANY PIZZAS AND HOW MUCH TIME 
    uint pizzas_ordered = rand_generator(&loc_seed, N_orderlow, N_orderhigh); //NUMBER BETWEEN 1 AND 5
    //printf("\nPIZZAS ORDERED %d\n", pizzas_ordered);
    //uint temp_plain = 0;
    //uint temp_special = 0;
    for (uint i = 0; i < pizzas_ordered; i++)
    {
        uint pizzaType = rand_generator(&loc_seed, 0, 100);
        mutex_lock(&pizza_count_mutex);
        (pizzaType <= P_plain) ? plain++ : special++;
        mutex_unlock(&pizza_count_mutex);
        //temp_plain++;
        //Update count of plain pizzas
        mutex_lock(&profit_mutex);
        profit += (pizzaType <= P_plain) ? C_plain : C_special;
        mutex_unlock(&profit_mutex);
    }

    mutex_lock(&cooks_count_mutex);
    while(cooks <= 0){
        //printf("\nH paraggelia %d den brhke paraskeuasth.Blocked...\n", id);
        cond_wait(&cooks_cond, &cooks_count_mutex);
    }
    //printf("\nH paraggelia %d paraskeuazete.\n", id);
    cooks--;
    //printf("\nAvailable cookers %d\n", cooks);
    mutex_unlock(&cooks_count_mutex);
    clock_get_time_check(&prep_time);

    sleep(T_prep * pizzas_ordered);
    //printf("\nOrder no.%d contains %d plain pizzas and %d special pizzas.\n", id, temp_plain, temp_special);

    mutex_lock(&ovens_count_mutex);
    while(ovens <= pizzas_ordered){
        //printf("\nH paraggelia %d den brhke oven.Blocked...\n", id);
        cond_wait(&ovens_cond, &ovens_count_mutex);
    }
    //printf("\nH paraggelia %d brhke ovens.\n", id);
    //clock_gettime(CLOCK_REALTIME, &bake_time);
    ovens = ovens - pizzas_ordered;
    //printf("\nAvailable ovens %d\n", ovens);
    mutex_unlock(&ovens_count_mutex);

    //LOCK MPAINEI EDW H PANV
    mutex_lock(&cooks_count_mutex); 
    //printf("\nH paraggelia %d paraskeuasthke epitixos! \n", id);
    cooks++;
    //printf("\nAvailable cookers %d\n", cooks);
    pthread_cond_signal(&cooks_cond);
    mutex_unlock(&cooks_count_mutex);
    clock_get_time_check(&bake_time);
    
    sleep(T_bake);
    //SHMEIO POY H PARAGGELIA BGAINEI APO TON TON FOYRNO
    mutex_lock(&packers_count_mutex);
    while(packers <= 0){
        //printf("\nH paraggelia %d den brhke packer.Blocked...\n", id);
        cond_wait(&packers_cond, &packers_count_mutex);
    }
    //printf("\nH paraggelia %d brhke packer.\n", id);
    packers--;
    //printf("\nAvailable packers %d\n", packers);
    mutex_unlock(&packers_count_mutex);

    clock_get_time_check(&pack_time);
    sleep(T_pack * pizzas_ordered);

    mutex_lock(&lockscreen_mutex);
    double until_packed = get_time_difference(&start_time, &pack_time);
    //printf("==========================================================================");
    printf("\nOrder #%d got ready approximately in %.3f minutes after being received.\n", id, round(until_packed));
    //printf("==========================================================================");
    mutex_unlock(&lockscreen_mutex);

    //APODESMEYONTAI OTAN TELEIVSEI TO PAKETARISMA
    mutex_lock(&ovens_count_mutex);
    //printf("\nH paraggelia %d bghke apo ton fourno.\n", id);
    ovens = ovens + pizzas_ordered;
    //printf("\nAvailable ovens %d\n", ovens);
    pthread_cond_signal(&ovens_cond);
    mutex_unlock(&ovens_count_mutex);
    
    //TIME OUT OF THE OVEN
    mutex_lock(&deliverers_count_mutex);
    while (deliverers <= 0){
        //printf("\nH paraggelia %d den brhke dianomea.Blocked...\n", id);
        cond_wait(&deliverers_cond, &deliverers_count_mutex);
    }
    //printf("\nH paraggelia %d brhke dianomea.\n", id);
    deliverers--;
    //printf("\nAvailable deliverers %d\n", deliverers);
    mutex_unlock(&deliverers_count_mutex);

    clock_get_time_check(&deliver_time);

    mutex_lock(&packers_count_mutex);
    //printf("\nH paraggelia %d paketaristhke.\n", id);
    packers++;
    //printf("\nAvailable packers %d\n", packers);
    pthread_cond_signal(&packers_cond);
    mutex_unlock(&packers_count_mutex);

    uint oneway_time = rand_generator(&loc_seed, T_dellow, T_delhigh); // RANDOM TIME FOR ONE-WAY TRIP [5, 15]
    //printf("\nONE WAY TIME: %d\n", oneway_time);
    sleep(oneway_time); //2*oneway_time -> TOTAL TIME THAT THE DELIVERY GUY IS OUT
    //printf("\nH paraggelia %d oloklhrwthike epityxws.\n", id);
    clock_get_time_check(&stop_time);

    sleep(oneway_time);

    mutex_lock(&deliverers_count_mutex);
    deliverers++;
    //printf("\nAvailable deliverers %d\n", deliverers);
    pthread_cond_signal(&deliverers_cond);
    mutex_unlock(&deliverers_count_mutex);

    //CALCULATE TIMES 
    //ADD MIN COOLING TIME AND WAITIG TIME
   // Cooling time
    mutex_lock(&total_cooling_time_mutex);
    cooling_time = get_time_difference(&pack_time, &stop_time);
    //printf("COOLING TIME: %.3f\n", round(cooling_time));
    
    mutex_lock(&satisfied_mutex);
    (cooling_time >= 15) ? disappointed++ : happy++;
    mutex_unlock(&satisfied_mutex);

    total_cooling_time += cooling_time;

    update_max_time(cooling_time, &max_cooling_time_mutex, &max_cooling_time);
    update_min_time(cooling_time, &min_cooling_time_mutex, &min_cooling_time);

    // Waiting time
    mutex_lock(&total_waiting_time_mutex);
    mutex_lock(&lockscreen_mutex);

    waiting_time = cooling_time + get_time_difference(&start_time, &pack_time); //CHANGE
    total_waiting_time += waiting_time;

    printf("\nOrder #%d delivered in %.3f minutes after being received.\n", id, round(waiting_time));
    mutex_unlock(&lockscreen_mutex);
    
    update_max_time(waiting_time, &max_waiting_time_mutex, &max_waiting_time);
    update_min_time(waiting_time, &min_waiting_time_mutex, &min_waiting_time);

    //printf("WAITING TIME: %.3f\n", round(waiting_time));

    mutex_unlock(&total_waiting_time_mutex);
    mutex_unlock(&total_cooling_time_mutex);

    pthread_exit(&id);
}

int main(int argc, char* argv[]){
    if(argc != 3){
        perror("Error: Invalid number of arguments.\n");
        exit(-1);
    }

    N_cust = atoi(argv[1]);

    if(N_cust < 1){
        perror("Error: Invalid number of customers.\n");
        exit(-1);
    }
    
    seed = atoi(argv[2]);
    
    //welcoming message
    printf("WELCOME TO OUR ONLINE PIZZA STORE! RUSH TO BECOME 1 OUT OF 100 TO BE SERVED.\n\n");

    uint ids[N_cust];
    pthread_t *threads;
    threads = malloc(N_cust * sizeof(pthread_t));
    if(threads == NULL){
        perror("Error: Failed to allocate memory.\n");
        exit(-1);
    }
    
    mutex_init();
    cond_init();

    for(int i = 0; i < N_cust; i++){
        ids[i] = i + 1;
        int loc_seed = seed + i + 1;
    	if(pthread_create(&threads[i], NULL, &order, (void*) &ids[i]) != 0){
            printf("Error: Failed to create order #%d.\n", i + 1);
            free(threads);
            mutex_destroy();
            cond_destroy();
            exit(-1);
        }
        sleep(rand_generator(&loc_seed, T_orderlow, T_orderhigh)); //NUMBER BETWEEN 1 AND 3   
    }

    for(int i = 0; i < N_cust; i++){
        if(pthread_join(threads[i], NULL) != 0){
            printf("Error: Failed to join order #%d.\n", i + 1);
            free(threads);
            mutex_destroy();
            cond_destroy();
            exit(-1);
        }
    }

    double avg_waiting_time = total_waiting_time / (double) succeeded;
    double avg_cooling_time = total_cooling_time / (double) succeeded;

    printf("\n");
    printf("\nMain: All (100) orders have been completed\n");
    printf("PROFIT: %d €\n", profit);
    printf("TYPE OF PIZZAS SOLD: %d plain and %d special\n", plain, special);
    printf("SUCCESSFUL ORDERS: %d\n", succeeded);
    printf("FAILED ORDERS: %d\n", failed);
    printf("HAPPY CUSTOMERS: %d\n", happy);
    printf("DISSAPOINTED CUSTOMERS: %d\n", disappointed);
    printf("MAXIMUM WAITING TIME: %.3f minutes\n", round(max_waiting_time));
    printf("MINIMUM WAITING TIME: %.3f minutes\n", round(min_waiting_time));
    printf("AVERAGE WAITING TIME: %.3f minutes\n", round(avg_waiting_time));
    printf("MAXIMUM COOLING TIME: %.3f minutes\n", round(max_cooling_time));
    printf("MINIMUM COOLING TIME: %.3f minutes\n", round(min_cooling_time));
    printf("AVERAGE COOLING TIME: %.3f minutes\n", round(avg_cooling_time));
    
    mutex_destroy();
    cond_destroy();
    free(threads);

    return 0;
}

//functions implementation OK
double get_time_difference(struct timespec *start_time, struct timespec *stop_time){
    double dif_sec = stop_time->tv_sec - start_time->tv_sec;
    double dif_nsec = (double)(stop_time->tv_nsec - start_time->tv_nsec) / BILLION;
    return dif_sec + dif_nsec;
}

uint rand_generator(uint *seed, uint min, uint max){
    return rand_r(seed) % (max - min + 1) + min;
}

void update_max_time(double new_time, pthread_mutex_t *max_mutex, double *cur_max){
    mutex_lock(max_mutex);
    if(new_time > *cur_max){
        *cur_max = new_time;
    }
    mutex_unlock(max_mutex);
}

void update_min_time(double new_time, pthread_mutex_t *min_mutex, double *cur_min){
    mutex_lock(min_mutex);
    if(new_time < *cur_min){
        *cur_min = new_time;
    }
    mutex_unlock(min_mutex);
}

void mutex_lock(pthread_mutex_t *lock){
    int rc = pthread_mutex_lock(lock);
    if(rc != 0){
        perror("Error: Failed to lock thread.\n");
        pthread_exit(&rc);
    }
}

void mutex_unlock(pthread_mutex_t *lock){
    int rc = pthread_mutex_unlock(lock);
    if(rc != 0){
        perror("Error: Failed to unlock thread.\n");
        pthread_exit(&rc);
    }
}

void clock_get_time_check(struct timespec *time){
    if(clock_gettime(CLOCK_REALTIME, time) != 0){
        perror("Error: Failed to calculate time.\n");
        exit(-1);
    }
}

void mutex_init(){
    if(pthread_mutex_init(&cooks_count_mutex, NULL) != 0){
        perror("Error: Failed to initialize mutex for cooks.\n");
        exit(-1);
    }

     if(pthread_mutex_init(&ovens_count_mutex, NULL) != 0){
        perror("Error: Failed to initialize mutex for ovens.\n");
        exit(-1);
    }

    if(pthread_mutex_init(&packers_count_mutex, NULL) != 0){
        perror("Error: Failed to initialize mutex for packers.\n");
        exit(-1);
    }

     if(pthread_mutex_init(&deliverers_count_mutex, NULL) != 0){
        perror("Error: Failed to initialize mutex for deliverers.\n");
        exit(-1);
    }

    if(pthread_mutex_init(&total_waiting_time_mutex, NULL) != 0){
        perror("Error: Failed to initialize mutex for total waiting time.\n"); 
        exit(-1);
    }

    if(pthread_mutex_init(&total_cooling_time_mutex, NULL) != 0){
        perror("Error: Failed to initialize mutex for total cooling time.\n"); 
        exit(-1);
    }

    if(pthread_mutex_init(&max_waiting_time_mutex, NULL) != 0){
        perror("Error: Failed to initialize mutex for max waiting time.\n");
        exit(-1);
    }

     if(pthread_mutex_init(&max_cooling_time_mutex, NULL) != 0){
        perror("Error: Failed to initialize mutex for max cooling time.\n");  
        exit(-1);
    }

    if(pthread_mutex_init(&min_waiting_time_mutex, NULL) != 0){
        perror("Error: Failed to initialize mutex for min waiting time.\n");
        exit(-1);
    } 

    if(pthread_mutex_init(&min_cooling_time_mutex, NULL) != 0){
        perror("Error: Failed to initialize muetx for min cooling time.\n");
        exit(-1);
    }

    if(pthread_mutex_init(&pizza_count_mutex, NULL) != 0){
        perror("Error: Failed to initialize mutex for pizzas count.\n"); 
        exit(-1);
    }

    if(pthread_mutex_init(&profit_mutex, NULL) != 0){
        perror("Error: Failed to initialize mutex for profit.\n"); 
        exit(-1);
    }

    if(pthread_mutex_init(&satisfied_mutex, NULL) != 0){
        perror("Error: Failed to initialize mutex for staisfaction.\n");
        exit(-1);
    }

       if(pthread_mutex_init(&lockscreen_mutex, NULL) != 0){
        perror("Error: Could not initialize mutex for lockscreen.\n"); //cor
        exit(-1);
    }
}

void mutex_destroy(){
      if(pthread_mutex_destroy(&cooks_count_mutex) != 0){
        perror("Error: Failed to destroy mutex for cooks.\n");
        exit(-1);
    }

      if(pthread_mutex_destroy(&ovens_count_mutex) != 0){
        perror("Error: Failed to destroy mutex for ovens.\n");
        exit(-1);
    }

    
    if(pthread_mutex_destroy(&packers_count_mutex) != 0){
        perror("Error: Failed to destroy mutex for packers.\n");
        exit(-1);
    }
    
    if(pthread_mutex_destroy(&deliverers_count_mutex) != 0){
        perror("Error: Failed to destroy mutex for deliverers.\n");
        exit(-1);
    }

       if(pthread_mutex_destroy(&total_waiting_time_mutex) != 0){
        perror("Error: Failed to destroy mutex for total waiting time.\n"); 
        exit(-1);
    }

    if(pthread_mutex_destroy(&total_cooling_time_mutex) != 0){
        perror("Error: Failed to destroy mutex for total cooling time.\n");
        exit(-1);
    }

    
    if(pthread_mutex_destroy(&max_waiting_time_mutex) != 0){
        perror("Error: Failed to destroy mutex for max waiting time.\n"); 
        exit(-1);
    }

     if(pthread_mutex_destroy(&max_cooling_time_mutex) != 0){
        perror("Error: Failed to destroy mutex for max cooling time.\n"); 
        exit(-1);
    }

    if(pthread_mutex_destroy(&min_waiting_time_mutex) != 0){
        perror("Error: Failed to destroy mutex for min waiting time.\n");
        exit(-1);
    } 

    if(pthread_mutex_destroy(&min_cooling_time_mutex) != 0){
        perror("Error: Failed to destroy mutex for min cooling time.\n");
        exit(-1);
    }

     if(pthread_mutex_destroy(&pizza_count_mutex) != 0){
        perror("Error: Failed to destroy mutex for pizzas count.\n"); 
        exit(-1);
    }

    if(pthread_mutex_destroy(&profit_mutex) != 0){
        perror("Error: Failed to destroy mutex for profit.\n");
        exit(-1);
    }

    if(pthread_mutex_destroy(&satisfied_mutex) != 0){
        perror("Error: Failed to destroy mutex for satisfaction.\n");
        exit(-1);
    }

    if(pthread_mutex_destroy(&lockscreen_mutex) != 0){
        perror("Error: Failed to destroy mutex for lockscreen.\n"); 
        exit(-1);
    }
}

void cond_init(){
    if(pthread_cond_init(&cooks_cond, NULL) != 0){
        perror("Error: Failed to initialize condition variable for cooks.\n");
        exit(-1);
    }

    if(pthread_cond_init(&ovens_cond, NULL) != 0){
        perror("Error: Failed to initialize condition variable for ovens.\n");
        exit(-1);
    }

    if(pthread_cond_init(&packers_cond, NULL) != 0){
        perror("Error: Failed to initialize condition variable for packers.\n");
        exit(-1);
    }

     if(pthread_cond_init(&deliverers_cond, NULL) != 0){
        perror("Error: Failed to initialize condition variable for deliverers.\n");
        exit(-1);
    }
}

void cond_wait(pthread_cond_t *cond, pthread_mutex_t *lock){
    if(pthread_cond_wait(cond, lock) != 0){
        perror("Error: Failed to wait.\n");
        exit(-1);
    }
}

void cond_destroy(){
    if(pthread_cond_destroy(&cooks_cond) != 0){
        perror("Error: Failed to destroy condition variable for cooks.\n");
        exit(-1);
    }

    if(pthread_cond_destroy(&ovens_cond) != 0){
        perror("Error: Failed to destroy condition variable for ovens.\n");
        exit(-1);
    }

    if(pthread_cond_destroy(&packers_cond) != 0){
        perror("Error: Failed to destroy condition variable for packers.\n");
        exit(-1);
    }

    if(pthread_cond_destroy(&deliverers_cond) != 0){
        perror("Error: Failed to destroy condition variable for deliverers.\n");
        exit(-1);
    }
}



