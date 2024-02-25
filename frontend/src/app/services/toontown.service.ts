import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from 'src/environments/environment';
import {News} from '../models/news';
import {ReleaseNotes} from '../models/release-notes';
import {FieldOffices} from '../models/field-offices';
import {Status} from '../models/status';
import {Districts} from '../models/districts';
import {ToonStats} from '../models/toonstats';
import {Apollo, gql, QueryRef} from "apollo-angular";
import {TTRPullDataQuery} from "../models/ttr-pull-data";

@Injectable({
  providedIn: 'root'
})
export class ToontownService {
  private toontownUrl: string = '/api/toontown'

  constructor(private http: HttpClient, private apollo: Apollo) { }

  public getToonStatsPageGQL(): QueryRef<TTRPullDataQuery> {
    return this.apollo.watchQuery({
      pollInterval: 10000,
      query: gql`
        query ToonStatsQuery {
          pullData {
            toonStats {
              amountBears
              amountBearsAbove100Laff
              amountBearsMaxed
              amountCats
              amountCatsAbove100Laff
              amountCatsMaxed
              amountCrocodiles
              amountCrocodilesAbove100Laff
              amountCrocodilesMaxed
              amountDeer
              amountDeerAbove100Laff
              amountDeerMaxed
              amountDogsAbove100Laff
              amountDogs
              amountDogsMaxed
              amountDucks
              amountDucksAbove100Laff
              amountDucksMaxed
              amountHorses
              amountHorsesAbove100Laff
              amountHorsesMaxed
              amountMice
              amountMiceAbove100Laff
              amountMiceMaxed
              amountMonkeys
              amountMonkeysAbove100Laff
              amountMonkeysMaxed
              amountOrganicDrop
              amountOrganicLure
              amountOrganicNone
              amountOrganicSound
              amountOrganicSquirt
              amountOrganicThrow
              amountOrganicToonUp
              amountOrganicTrap
              amountPigs
              amountPigsAbove100Laff
              amountPigsMaxed
              amountRabbits
              amountRabbitsAbove100Laff
              amountRabbitsMaxed
              amountTier8Bossbot
              amountTier8Cashbot
              amountTier8Lawbot
              amountTier8Sellbot
              amountToons
              amountToonsAbove100Laff
              amountToonsWithDrop
              amountToonsMaxed
              amountToonsWithDropAbove100Laff
              amountToonsWithDropMaxed
              amountToonsWithLure
              amountToonsWithLureMaxed
              amountToonsWithLureAbove100Laff
              amountToonsWithSound
              amountToonsWithSoundAbove100Laff
              amountToonsWithSoundMaxed
              amountToonsWithSquirt
              amountToonsWithSquirtAbove100Laff
              amountToonsWithSquirtMaxed
              amountToonsWithThrow
              amountToonsWithThrowAbove100Laff
              amountToonsWithThrowMaxed
              amountToonsWithToonUp
              amountToonsWithToonUpAbove100Laff
              amountToonsWithToonUpMaxed
              amountToonsWithTrap
              amountToonsWithTrapAbove100Laff
              amountToonsWithTrapMaxed
            }
          }
        }`
    })
  }

  public getNewsPageGQL() : QueryRef<TTRPullDataQuery> {
    return this.apollo.watchQuery({
      pollInterval: 10000,
      query: gql`
        {
          pullData {
            districts {
              totalPopulation
            }
            news {
              author
              body
              date
              image
              postId
              title
            }
            releaseNotes {
              body
              date
              noteId
              slug
            }
            status {
              open
              status
            }
          }
        }`
    })
  }

  public getLoginPageGQL() : QueryRef<TTRPullDataQuery> {
    return this.apollo.watchQuery({
      pollInterval: 10000,
      query: gql`
        {
          pullData {
            status {
              open
              status
            }
          }
        }`
    })
  }

  public getDistrictsPageGQL() : QueryRef<TTRPullDataQuery> {
    return this.apollo.watchQuery({
      pollInterval: 10000,
      query: gql`
        {
          pullData {
            districts {
              totalPopulation
              districts {
                districtStatus
                invasion {
                  cog
                  cogsDefeated
                  totalCogs
                }
                name
                population
              }
            }
          }
        }`
    })
  }

  public getFieldOfficesPageGQL() : QueryRef<TTRPullDataQuery> {
    return this.apollo.watchQuery({
      pollInterval: 10000,
      query: gql`
        {
          pullData {
            fieldOffices {
              fieldOffices {
                annexes
                department
                difficulty
                expiring
                open
                zone
              }
              lastUpdated
            }
          }
        }`
    })
  }

  public getFieldOffices(): Observable<FieldOffices> {
    return this.get<FieldOffices>('/fieldoffices');
  }

  private get<T>(endpoint: string): Observable<T> {
    return this.http.get<T>(
      environment.baseUrl + this.toontownUrl + endpoint,
      {
        headers: environment.headers,
      }
    )
  }
}
